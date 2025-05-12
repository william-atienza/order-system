package org.project.order.controller;

import jakarta.websocket.server.PathParam;
import org.project.order.dto.OrderRecord;
import org.project.order.dto.OrderRequest;
import org.project.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/index")
    public String index(){
        return "index page";
    }

    @PostMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderRecord> create(@RequestBody OrderRequest request) throws ExecutionException, InterruptedException {
        CompletableFuture<OrderRecord> future = orderService.create(request);
        return new ResponseEntity(future.get(), HttpStatus.CREATED);
    }

    @GetMapping("/orders/{orderId}/{accountId}")
    public ResponseEntity<OrderRecord> getOrder(@PathVariable("orderId") String orderId,
                                                @PathVariable("accountId") String accountId){
        return ResponseEntity.ok(orderService.getOrder(orderId, accountId));
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderRecord>> getOrders(@PathParam("accountId") String accountId,
                                                       @PathParam("page") int page,
                                                       @PathParam("size") int size,
                                                       @PathParam("sortBy") String sortBy,
                                                       @PathParam("sortDir") String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.ok(orderService.getOrders(accountId, pageable));
    }
}
