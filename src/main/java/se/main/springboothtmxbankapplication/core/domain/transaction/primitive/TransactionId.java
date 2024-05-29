package se.main.springboothtmxbankapplication.domain.transaction.primitive;

import se.main.springboothtmxbankapplication.primitive.AggregateId;

import java.util.UUID;

public class TransactionId extends AggregateId {
    public TransactionId(UUID id) {
        super(id);
    }

    public static TransactionId from(UUID id) {
        return new TransactionId(id);
    }
}
