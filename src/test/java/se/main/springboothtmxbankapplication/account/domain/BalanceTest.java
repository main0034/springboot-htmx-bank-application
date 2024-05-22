package se.main.springboothtmxbankapplication.account.domain;


import com.google.common.base.VerifyException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BalanceTest {

    @Test
    void can_create() {
        assertThatNoException().isThrownBy(() -> new Balance(BigInteger.ZERO));
    }

    @Test
    void balance_must_not_be_null() {
        assertThatThrownBy(() -> new Balance(null))
                .isInstanceOf(VerifyException.class)
                .hasMessage("balanceInCents must not not be null");
    }

}