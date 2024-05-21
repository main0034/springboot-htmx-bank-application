package se.main.springboothtmxbankapplication.account.domain;

import se.main.springboothtmxbankapplication.primitives.AggregateId;

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
