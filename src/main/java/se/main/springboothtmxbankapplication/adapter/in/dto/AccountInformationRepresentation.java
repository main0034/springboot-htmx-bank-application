package se.main.springboothtmxbankapplication.adapter.in.dto;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public record AccountInformationRepresentation(
        UUID accountId,
        BigInteger balanceInCents,
        List<TransactionRepresentation> transactions
) {
}
