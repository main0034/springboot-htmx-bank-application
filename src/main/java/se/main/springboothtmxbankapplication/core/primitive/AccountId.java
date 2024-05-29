package se.main.springboothtmxbankapplication.primitive;

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
