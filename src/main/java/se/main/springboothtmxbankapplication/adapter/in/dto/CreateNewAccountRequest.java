package se.main.springboothtmxbankapplication.adapter.in.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;
import java.util.UUID;

public record CreateNewAccountRequest(
        @NotNull UUID customerId,
        @NotNull BigInteger initialCreditInCents
) {
}
