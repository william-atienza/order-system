package org.project.dto;

import java.time.Instant;
import java.util.UUID;

public record AccountRecord(Long id, Instant createdOn) {
}
