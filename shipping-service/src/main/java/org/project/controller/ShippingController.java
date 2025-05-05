package org.project.controller;

import org.project.dto.ShippingRecord;
import org.project.service.ShippingService;
import org.project.type.ShipmentStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
