package se.main.springboothtmxbankapplication.domain.account.primitve;

import org.apache.commons.lang3.Validate;
import se.main.springboothtmxbankapplication.primitive.Amount;

import java.math.BigInteger;

public record Balance(
        BigInteger balanceInCents
) {
    public Balance {
        Validate.notNull(balanceInCents, "balanceInCents must not not be null");
    }

    public static Balance from(BigInteger balanceInCents) {
        return new Balance(balanceInCents);
    }

    public Balance add(Amount amount) {
        BigInteger newBalance = balanceInCents.add(amount.value());
        return Balance.from(newBalance);
    }

    public Balance subtract(Amount amount) {
        BigInteger newBalance = balanceInCents.subtract(amount.value());
        return Balance.from(newBalance);
    }

    public boolean hasCoverage(Amount amount) {
        return balanceInCents.compareTo(amount.value()) >= 0;
    }
}
