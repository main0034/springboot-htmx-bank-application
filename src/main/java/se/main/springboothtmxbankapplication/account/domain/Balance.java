package se.main.springboothtmxbankapplication.account.domain;

import com.google.common.base.Verify;

import java.math.BigInteger;

public record Balance(
        BigInteger balanceInCents
) {
    public Balance {
        Verify.verifyNotNull(balanceInCents, "balanceInCents must not not be null");
    }

    public static Balance from(BigInteger balanceInCents) {
        return new Balance(balanceInCents);
    }
}
