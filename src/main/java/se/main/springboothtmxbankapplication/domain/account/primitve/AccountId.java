package se.main.springboothtmxbankapplication.domain.account.primitve;

import se.main.springboothtmxbankapplication.primitive.AggregateId;

import java.util.UUID;

public class AccountId extends AggregateId {

    private AccountId(UUID id) {
        super(id);
    }

    public static AccountId from(UUID id) {
        return new AccountId(id);
    }

    @Override
    public String toString() {
        return toString("AccountId");
    }
}
