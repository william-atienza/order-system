package com.project.dto;

import com.project.type.ShipmentStatus;

import java.time.Instant;

public record ShippingRecord(
        String orderId, String accountId, Instant deliveryDate, String deliveryAddress, Instant deliveredOn, ShipmentStatus status, String trackingNumber) {
}
