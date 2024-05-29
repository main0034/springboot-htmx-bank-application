package se.main.springboothtmxbankapplication.domain.transaction.aggregate;

import org.apache.commons.lang3.Validate;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;

import java.util.Optional;
import java.util.UUID;

public class Transaction {

    private final TransactionId id;
    private final AccountId accountId;
    private final TransactionType type;
    private final Amount amount;
    private final Timestamp timestamp;
    private final Description description;

    public Transaction(TransactionId id,
                       AccountId accountId,
                       TransactionType type,
                       Amount amount,
                       Timestamp timestamp,
                       Description description) {
        this.id = Validate.notNull(id, "id must not be null");
        this.accountId = Validate.notNull(accountId, "accountId must not be null");
        this.type = Validate.notNull(type, "type must not be null");
        this.amount = Validate.notNull(amount, "amount must not be null");
        this.timestamp = Validate.notNull(timestamp, "timestamp must not be null");
        this.description = description;
    }

    public static Transaction createNew(AccountId accountId, TransactionType type, Amount amount) {
        return new Transaction(
                TransactionId.from(UUID.randomUUID()),
                accountId,
                type,
                amount,
                Timestamp.now(),
                null
        );
    }

    public TransactionId id() {
        return id;
    }

    public AccountId accountId() {
        return accountId;
    }

    public TransactionType type() {
        return type;
    }

    public Amount amount() {
        return amount;
    }

    public Timestamp timestamp() {
        return timestamp;
    }

    public Optional<Description> description() {
        return Optional.ofNullable(description);
    }
}
