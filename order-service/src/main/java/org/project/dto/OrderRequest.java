package org.project.dto;

public record OrderRequest(Long accountId, ShippingRecord shippingRecord) {
}
