package se.main.springboothtmxbankapplication.account.command;

import com.google.common.base.VerifyException;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.account.domain.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CreateAccountCommandTest {

    private static final CustomerId CUSTOMER_ID = CustomerId.from(UUID.randomUUID());
    private static final Balance BALANCE = Balance.from(BigInteger.ZERO);

    @Test
    void can_create() {
        assertThatNoException().isThrownBy(() -> new CreateAccountCommand(CUSTOMER_ID, BALANCE));
    }

    @Test
    void customerId_must_not_be_null() {
        assertThatThrownBy(() -> new CreateAccountCommand(null, BALANCE))
                .isInstanceOf(VerifyException.class)
                .hasMessage("customerId must not be null");
    }

    @Test
    void balance_must_not_be_null() {
        assertThatThrownBy(() -> new CreateAccountCommand(CUSTOMER_ID, null))
                .isInstanceOf(VerifyException.class)
                .hasMessage("balance must not be null");
    }

}