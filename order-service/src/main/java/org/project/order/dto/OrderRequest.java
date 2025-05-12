package org.project.order.dto;

public record OrderRequest(String accountId, ShippingRecord shippingRecord) {
}
