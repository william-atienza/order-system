package org.project.shipping.controller;

import org.project.shipping.dto.ShippingRecord;
import org.project.shipping.service.ShippingService;
import org.project.shipping.type.ShipmentStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShippingController {

    private final ShippingService service;

    ShippingController(ShippingService service){
        this.service = service;
    }

    @PostMapping(path = "/shipping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentStatus> update(@RequestBody ShippingRecord shippingRecord){
        service.update(shippingRecord);
        return ResponseEntity.ok(shippingRecord.status());
    }
}
