package se.main.springboothtmxbankapplication.adapter.gui.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class AccountRepresentation {

    private final String accountId;
    private final String customerId;
    private final String balance;

    public AccountRepresentation(
            String accountId,
            String customerid,
            BigInteger balanceInCents
    ) {
        this.accountId = accountId;
        this.customerId = customerid;
        BigDecimal bigDecimal = new BigDecimal(balanceInCents);
        BigDecimal balanceAsDollars = bigDecimal.divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
        this.balance = "%.2f".formatted(balanceAsDollars);
    }

    public String accountId() {
        return accountId;
    }

    public String customerId() {
        return customerId;
    }

    public String balance() {
        return balance + "$";
    }

}
