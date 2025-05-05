package org.project.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.entity.Order;
import org.project.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    public static final String TOPIC = "order";

    private KafkaTemplate<String, String> kafkaTemplate;

    KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order){
        try {
            kafkaTemplate.send(TOPIC, order.getId().toString() , Mapper.INSTANCE.toString(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Producer produced the message {}", order);
    }

}
