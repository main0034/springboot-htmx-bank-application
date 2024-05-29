package se.main.springboothtmxbankapplication.core.primitive;

import org.apache.commons.lang3.Validate;

import java.math.BigInteger;

public record Amount(BigInteger value) {
    public Amount {
        Validate.notNull(value, "value must not not be null");
        Validate.isTrue(value.compareTo(BigInteger.ZERO) >= 0, "value must be greater than or equal to zero");
    }

    public static Amount from(long value) {
        return new Amount(BigInteger.valueOf(value));
    }

    public static Amount from(BigInteger value) {
        return new Amount(value);
    }
}
