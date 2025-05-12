package org.project.order.dto;

import org.project.order.type.ShipmentStatus;

import java.time.Instant;

public record ShippingRecord(String orderId, String accountId, Instant deliveryDate, String deliveryAddress, Instant deliveredOn, ShipmentStatus status) {
}
