package org.project.shipping.kafka;

import org.project.shipping.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    Logger logger = LoggerFactory.getLogger(OrderListener.class);

    private final ShippingService service;
    OrderListener(ShippingService service){
        this.service = service;
    }

    @KafkaListener(topics = "order")
    public void listen(String data){ //Supposed to be the object as payload instead of the converted string. Cant fix the serialization error in the producer.
        logger.info("listened data: {}", data);
        service.save(data);
    }
}
