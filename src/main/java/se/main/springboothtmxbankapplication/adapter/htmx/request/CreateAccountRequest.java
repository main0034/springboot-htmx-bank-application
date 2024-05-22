package se.main.springboothtmxbankapplication.adapter.htmx.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAccountRequest(
        @NotNull UUID customerId,
        @NotNull Long initialCredit) {
}