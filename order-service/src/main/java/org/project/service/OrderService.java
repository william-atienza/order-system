package org.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.dto.OrderRecord;
import org.project.dto.OrderRequest;
import org.project.entity.Account;
import org.project.entity.Order;
import org.project.exception.ApplicationException;
import org.project.kafka.KafkaProducer;
import org.project.repository.AccountRepository;
import org.project.repository.OrderRepository;
import org.project.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    final OrderRepository orderRepository;
    final AccountRepository accountRepository;
    final KafkaProducer kafkaProducer;

    OrderService(OrderRepository orderRepository, AccountRepository accountRepository, KafkaProducer kafkaProducer){
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional
    public OrderRecord create(OrderRequest request) {
        Optional<Account> account = accountRepository.findById(request.accountId());
        if(account.isEmpty()){
            throw new ApplicationException("Account is not found");
        }
        Order order = null;
        log.info("shipment: {}", request.shippingRecord());
        try {
            order = new Order(Mapper.INSTANCE.toString(request.shippingRecord()), account.get());
        } catch (JsonProcessingException e) {
            log.error("error here: {}",e);
            throw new ApplicationException("Error in converting Shipment data to String.");
        }
        orderRepository.save(order);
        kafkaProducer.send(order);
        return new OrderRecord(order.getId(), order.getAccount().getId(), null, null, null);
    }

    public OrderRecord getOrder(OrderRecord orderRecord){
        Optional<Account> account = accountRepository.findById(orderRecord.accountId());
        if(account.isEmpty()){
            throw new ApplicationException("Account is not found");
        }
        Order order = orderRepository.findOrderByIdAndAccount(orderRecord.id(), account.get());
        if(order == null){
            throw new ApplicationException("Order is not found");
        }
        //ShipmentRecord shipmentRecord = new ShipmentRecord();
        return new OrderRecord(order.getId(), order.getAccount().getId(), null, null, null);
    }

    public Order getOrder(Long accountId, UUID productId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isEmpty()){
            throw new ApplicationException("Account not found");
        }
        return orderRepository.findOrderByIdAndAccount(productId, account.get());
    }

    public List<Order> getAll(Long accountId, UUID productId, Pageable pageable){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isEmpty()){
            throw new ApplicationException("Account not found");
        }
        return orderRepository.findOrdersByIdAndAccount(productId, account.get(), pageable);
    }
}
