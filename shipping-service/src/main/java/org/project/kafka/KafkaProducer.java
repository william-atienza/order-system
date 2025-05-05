package org.project.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.dto.ShippingRecord;
import org.project.entity.Shipping;
import org.project.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    public static final String TOPIC = "shipping";

    private final KafkaTemplate<String, String> kafkaTemplate;

    KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ShippingRecord shippingRecord){
        try {
            kafkaTemplate.send(TOPIC, shippingRecord.orderId(), Mapper.INSTANCE.toString(shippingRecord));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Produced shipping message {}", shippingRecord);
    }
}