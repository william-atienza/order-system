package org.project.dto;

import java.time.Instant;
import java.util.UUID;

public record OrderRecord(UUID id, Long accountId, Instant createdOn, Instant lastUpdatedOn,
                          ShippingRecord shippingRecord) {
}
