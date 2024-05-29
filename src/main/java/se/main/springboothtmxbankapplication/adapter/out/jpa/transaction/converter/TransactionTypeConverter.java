package se.main.springboothtmxbankapplication.adapter.out.jpa.transaction.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;

@Component
public class TransactionTypeConverter {

    public int toDbId(TransactionType type) {
        return switch (type) {
            case INCOMING -> 0;
            case OUTGOING -> 1;
        };
    }

    public TransactionType toTransactionType(int dbId) {
        return switch (dbId) {
            case 0 -> TransactionType.INCOMING;
            case 1 -> TransactionType.OUTGOING;
            default -> throw new IllegalArgumentException("Uknown transactionTypeId: " + dbId);
        };
    }
}
