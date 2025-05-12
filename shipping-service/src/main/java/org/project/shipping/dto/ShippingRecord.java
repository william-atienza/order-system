package org.project.shipping.dto;

import org.project.shipping.type.ShipmentStatus;

import java.time.Instant;

public record ShippingRecord(
        String orderId, String accountId, Instant deliveryDate, String deliveryAddress, Instant deliveredOn, ShipmentStatus status) {
}
