package org.project.dto;

public record OrderRequest(String accountId, ShippingRecord shippingRecord) {
}
