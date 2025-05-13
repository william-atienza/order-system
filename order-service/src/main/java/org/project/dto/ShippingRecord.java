package org.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.project.type.ShipmentStatus;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShippingRecord(String orderId, String accountId, Instant deliveryDate, String deliveryAddress,
                             Instant deliveredOn, ShipmentStatus status, String trackingNumber) {
    public ShippingRecord(String orderId, String accountId, Instant deliveryDate, String deliveryAddress,
                   Instant deliveredOn, ShipmentStatus status, String trackingNumber){
        this.orderId = orderId;
        this.accountId = accountId;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveredOn = deliveredOn;
        this.status = (status == null ? ShipmentStatus.REQUESTED : status);
        this.trackingNumber = trackingNumber;
    }
}
