package se.main.springboothtmxbankapplication.core.domain.transaction.aggregate;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TransactionTest {

    private static final TransactionId ID = TransactionId.from(UUID.randomUUID());
    private static final AccountId ACCOUNT_ID = AccountId.from(UUID.randomUUID());
    private static final TransactionType TYPE = TransactionType.OUTGOING;
    private static final Amount AMOUNT = Amount.from(1337);
    private static final Timestamp TIMESTAMP = Timestamp.now();
    private static final Description DESCRIPTION = Description.from("Description");

    @Test
    void can_create_with_description() {
        assertThatNoException().isThrownBy(() -> new Transaction(
                ID,
                ACCOUNT_ID,
                TYPE,
                AMOUNT,
                TIMESTAMP,
                DESCRIPTION
        ));
    }

    @Test
    void can_create_without_description() {
        assertThatNoException().isThrownBy(() -> new Transaction(
                ID,
                ACCOUNT_ID,
                TYPE,
                AMOUNT,
                TIMESTAMP,
                null
        ));
    }

    @Test
    void id_must_not_be_null() {
        assertThatThrownBy(() -> new Transaction(
                null,
                ACCOUNT_ID,
                TYPE,
                AMOUNT,
                TIMESTAMP,
                DESCRIPTION
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id must not be null");
    }

    @Test
    void accountId_must_not_be_null() {
        assertThatThrownBy(() -> new Transaction(
                ID,
                null,
                TYPE,
                AMOUNT,
                TIMESTAMP,
                DESCRIPTION
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("accountId must not be null");
    }

    @Test
    void type_must_not_be_null() {
        assertThatThrownBy(() -> new Transaction(
                ID,
                ACCOUNT_ID,
                null,
                AMOUNT,
                TIMESTAMP,
                DESCRIPTION
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("type must not be null");
    }

    @Test
    void amount_must_not_be_null() {
        assertThatThrownBy(() -> new Transaction(
                ID,
                ACCOUNT_ID,
                TYPE,
                null,
                TIMESTAMP,
                DESCRIPTION
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("amount must not be null");
    }

    @Test
    void timestamp_must_not_be_null() {
        assertThatThrownBy(() -> new Transaction(
                ID,
                ACCOUNT_ID,
                TYPE,
                AMOUNT,
                null,
                DESCRIPTION
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("timestamp must not be null");
    }
}