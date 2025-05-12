package org.project.shipping.dto;

import java.time.Instant;

public record OrderRecord(String orderId, String accountId, Instant createdOn, Instant lastUpdatedOn,
                          ShippingRecord shippingRecord) {
}
