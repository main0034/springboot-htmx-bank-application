package se.main.springboothtmxbankapplication.adapter.in.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionRepresentation(
        UUID id,
        String type,
        BigInteger amount,
        LocalDateTime timestamp,
        String description
) {
}
