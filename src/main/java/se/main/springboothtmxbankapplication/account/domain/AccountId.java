package se.main.springboothtmxbankapplication.account.domain;

import se.main.springboothtmxbankapplication.primitive.AggregateId;

import java.io.Serializable;
import java.util.UUID;

public class AccountId extends AggregateId implements Serializable {

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
