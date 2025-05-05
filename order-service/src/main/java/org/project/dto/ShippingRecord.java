package org.project.dto;

import org.project.type.ShipmentStatus;

import java.time.Instant;
import java.util.UUID;

public record ShippingRecord(UUID orderId, Instant deliveryDate, String deliveryAddress, ShipmentStatus status) {
}
