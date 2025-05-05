package org.project.kafka;

import org.project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ShippingListener {
    Logger logger = LoggerFactory.getLogger(ShippingListener.class);

    private final OrderService service;
    ShippingListener(OrderService service){
        this.service = service;
    }

    @KafkaListener(topics = "shipping")
    public void listen(String data){ //Supposed to be the object as payload instead of the converted string. Cant fix the serialization error in the producer.
        logger.info("listened data: {}", data);
        service.update(data);
    }
}
