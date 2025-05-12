package org.project.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.order.dto.OrderRecord;
import org.project.order.dto.OrderRequest;
import org.project.order.dto.ShippingRecord;
import org.project.order.entity.Account;
import org.project.order.entity.Order;
import org.project.order.exception.ApplicationException;
import org.project.order.kafka.KafkaProducer;
import org.project.order.repository.AccountRepository;
import org.project.order.repository.OrderPagingAndSortingRepository;
import org.project.order.repository.OrderRepository;
import org.project.order.type.ShipmentStatus;
import org.project.order.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    final OrderRepository orderRepository;
    final OrderPagingAndSortingRepository orderPagingAndSortingRepository;
    final AccountRepository accountRepository;
    final KafkaProducer kafkaProducer;

    OrderService(OrderRepository orderRepository, OrderPagingAndSortingRepository orderPagingAndSortingRepository, AccountRepository accountRepository, KafkaProducer kafkaProducer){
        this.orderRepository = orderRepository;
        this.orderPagingAndSortingRepository = orderPagingAndSortingRepository;
        this.accountRepository = accountRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional
    @Async(value = "customTPTaskExecutor")
    public CompletableFuture<OrderRecord> create(OrderRequest request) {
        Optional<Account> optional = accountRepository.findById(request.accountId());
        if(optional.isEmpty()){
            throw new ApplicationException("Account is not found");
        }
        Account account = optional.get();
        Order order = null;
        ShippingRecord shippingRecord = new ShippingRecord(null, account.getId(), request.shippingRecord().deliveryDate() == null? null: request.shippingRecord().deliveryDate(),
                request.shippingRecord().deliveryAddress(), null,  ShipmentStatus.REQUESTED);

        log.info("shipment: {}", shippingRecord);
        try {
            order = new Order(Mapper.INSTANCE.toString(shippingRecord), account);
        } catch (JsonProcessingException e) {
            log.error("error here: {}",e);
            throw new ApplicationException("Error in converting Shipment data to String.");
        }
        orderRepository.save(order);

        OrderRecord orderRecord = new OrderRecord(order.getId(), order.getAccount().getId(), order.getCreatedOn(), order.getLastUpdatedOn(), shippingRecord);
        kafkaProducer.send(orderRecord);
        return CompletableFuture.completedFuture(orderRecord);
    }

    public void update(String orderRecordJson) {
        try {
            ShippingRecord shippingRecord = Mapper.INSTANCE.toObject(orderRecordJson, ShippingRecord.class);
            Optional<Order> optional = orderRepository.findById(shippingRecord.orderId());
            if(optional.isEmpty()){
                log.error("Order#{} does not exist.", shippingRecord.orderId());
            }

            Order order = optional.get();
            order.setShipping(Mapper.INSTANCE.toString(shippingRecord));
            orderRepository.save(order);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Cannot convert json to OrderRecord.");
        }
    }

    public OrderRecord getOrder(String orderId, String accountId){
        log.info("accountId: {}", accountId);
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isEmpty()){
            throw new ApplicationException("Account not found");
        }
        Order order = orderRepository.findOrderByIdAndAccount(orderId, account.get());
        try {
            return new OrderRecord(orderId, accountId, order.getCreatedOn(), order.getLastUpdatedOn(),
                    Mapper.INSTANCE.toObject(order.getShipping(), ShippingRecord.class));
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Cannot convert json to OrderRecord.");
        }
    }

    public List<OrderRecord> getOrders(String accountId, Pageable pageable){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isEmpty()){
            throw new ApplicationException("Account not found");
        }
        List<Order> orders = orderPagingAndSortingRepository.findOrdersByAccount(account.get(), pageable);
        //List<Order> orders = orderRepository.findOrdersByAccount(account.get(), pageable);
        return orders.stream()
                        .map(order -> {
                            try {
                                return new OrderRecord(order.getId(), order.getAccount().getId(), order.getCreatedOn(),
                                        order.getLastUpdatedOn(), Mapper.INSTANCE.toObject(order.getShipping(), ShippingRecord.class));
                            } catch (JsonProcessingException e) {
                                throw new ApplicationException("Cannot convert json to ShippingRecord.");
                            }
                        })
                        .collect(Collectors.toUnmodifiableList());
    }
}
