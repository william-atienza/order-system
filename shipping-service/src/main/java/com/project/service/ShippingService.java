package com.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.dto.OrderRecord;
import com.project.dto.ShippingRecord;
import com.project.entity.Shipping;
import com.project.exception.ApplicationException;
import com.project.kafka.KafkaProducer;
import com.project.repository.ShippingRepository;
import com.project.type.ShipmentStatus;
import com.project.util.Mapper;
import com.project.util.TrackingNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                    orderRecord.shippingRecord().deliveryAddress(), orderRecord.shippingRecord().deliveredOn(), ShipmentStatus.PROCESSING, null);
            repository.save(shipping);
            kafkaProducer.send(new ShippingRecord(orderRecord.orderId(), orderRecord.accountId(), orderRecord.shippingRecord().deliveryDate(),
                    orderRecord.shippingRecord().deliveryAddress(), orderRecord.shippingRecord().deliveredOn(), ShipmentStatus.PROCESSING, null));
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
        //Create a new shipping record to have a historical data
        String trackingNumber = null;
        if(ShipmentStatus.IN_TRANSIT == shippingRecord.status()){
            //We just hardcode PH as originating country. By right it should be pulled from somewhere
            trackingNumber = TrackingNumber.INSTANCE.generate(shippingRecord.orderId(), "PH");
        }
        Shipping shipping = new Shipping(shippingRecord.orderId(),  shippingRecord.accountId(), shippingRecord.deliveryDate(),
                shippingRecord.deliveryAddress(), shippingRecord.deliveredOn(),  shippingRecord.status(), trackingNumber);
        repository.save(shipping);
        kafkaProducer.send(new ShippingRecord(shippingRecord.orderId(),  shippingRecord.accountId(), shippingRecord.deliveryDate(),
                shippingRecord.deliveryAddress(), shippingRecord.deliveredOn(),  shippingRecord.status(), trackingNumber));
        return shipping.getStatus();
    }
}
