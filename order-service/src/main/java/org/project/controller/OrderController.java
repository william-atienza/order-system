package org.project.controller;

import org.project.dto.OrderRecord;
import org.project.dto.OrderRequest;
import org.project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderRecord> create(@RequestBody OrderRequest request){
        OrderRecord orderRecord = orderService.create(request);
        return new ResponseEntity(orderRecord, HttpStatus.CREATED);
    }

    @GetMapping("/order")
    public ResponseEntity<OrderRecord> getOrder(@RequestBody OrderRecord request){
        return ResponseEntity.ok(orderService.getOrder(request));
    }
}
