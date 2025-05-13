package org.project.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.project.type.ShipmentStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Instant createdOn;
    private Instant deliveryDate;
    private Instant deliveredOn;
    private String deliveryAddress;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
    private String orderId;
    private String accountId;
    private String trackingNumber;

    public Shipping(){}
    public Shipping(String orderId, String accountId, Instant deliveryDate, String deliveryAddress, Instant deliveredOn, ShipmentStatus status, String trackingNumber){
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.orderId = orderId;
        this.accountId = accountId;
        this.deliveredOn = deliveredOn;
        this.trackingNumber = trackingNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Instant deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(Instant deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
