package org.project.order.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.order.dto.OrderRecord;
import org.project.order.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    public static final String TOPIC = "order";

    private final KafkaTemplate<String, String> kafkaTemplate;

    KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderRecord orderRecord){
        try {
            //Supposed to be passing the object as payload instead of the converted string. Cant fix the serialization error.
            kafkaTemplate.send(TOPIC, orderRecord.orderId().toString(), Mapper.INSTANCE.toString(orderRecord));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Produced order message {}", orderRecord);
    }

}
