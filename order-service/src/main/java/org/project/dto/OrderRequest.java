package org.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequest(String accountId, @JsonProperty("shipping") ShippingRecord shippingRecord) {
}
