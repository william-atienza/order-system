package org.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.project.dto.OrderRecord;
import org.project.dto.ShippingRecord;
import org.project.entity.Shipping;
import org.project.exception.ApplicationException;
import org.project.kafka.KafkaProducer;
import org.project.repository.ShippingRepository;
import org.project.type.ShipmentStatus;
import org.project.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShippingService {

    private static final Logger log = LoggerFactory.getLogger(ShippingService.class);
    private final ShippingRepository repository;
    private final KafkaProducer kafkaProducer;
    ShippingService(ShippingRepository repository, KafkaProducer kafkaProducer){
        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
    }

    public void save(String json){
        try {
            //There should be some validations here to check if the request shipping is already existing
            OrderRecord orderRecord = Mapper.INSTANCE.toObject(json, OrderRecord.class);
            Shipping shipping = new Shipping(orderRecord.orderId(), orderRecord.accountId(), orderRecord.shippingRecord().deliveryDate(),
                    orderRecord.shippingRecord().deliveryAddress(), orderRecord.shippingRecord().deliveredOn(), ShipmentStatus.PROCESSING);
            repository.save(shipping);
            kafkaProducer.send(new ShippingRecord(orderRecord.orderId(), orderRecord.accountId(), orderRecord.shippingRecord().deliveryDate(),
                    orderRecord.shippingRecord().deliveryAddress(), orderRecord.shippingRecord().deliveredOn(), ShipmentStatus.PROCESSING));
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Cannot convert json to OrderRecord.");
        }
    }

    public ShipmentStatus update(ShippingRecord shippingRecord){
        log.info("shippingRecord.orderId(): {}",shippingRecord.orderId());
        Optional<Shipping> optional = repository.findLatestShippingByOrderId(shippingRecord.orderId());
        if(optional.isEmpty()){
            throw  new ApplicationException("Cannot find shipping record.");
        }

        //There should be some validations here
        //Shipping old = optional.get();
        //Create a new shipping record to have a historical data
        Shipping shipping = new Shipping(shippingRecord.orderId(),  shippingRecord.accountId(), shippingRecord.deliveryDate(),
                shippingRecord.deliveryAddress(), shippingRecord.deliveredOn(),  shippingRecord.status());
        repository.save(shipping);
        kafkaProducer.send(shippingRecord);
        return shipping.getStatus();
    }
}
