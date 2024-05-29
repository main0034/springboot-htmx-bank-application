package se.main.springboothtmxbankapplication;

import se.main.springboothtmxbankapplication.adapter.out.jpa.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.math.BigInteger;
import java.util.UUID;

public final class AccountTestFactory {

    public static final Balance INITIAL_BALANCE_ZERO = Balance.from(BigInteger.ZERO);

    public static Account accountWithBalanceZero() {
        return new Account(newAccountId(), newCustomerId(), INITIAL_BALANCE_ZERO);
    }

    public static Account accountWithBalance(long balance) {
        return new Account(newAccountId(), newCustomerId(), Balance.from(BigInteger.valueOf(balance)));
    }

    public static Account accountWithCustomerId(CustomerId customerId) {
        return new Account(newAccountId(), customerId, INITIAL_BALANCE_ZERO);
    }

    public static AccountEntity entityWithBalanceZero() {
        return new AccountEntity(
                newAccountId().getId(),
                newCustomerId().getId(),
                INITIAL_BALANCE_ZERO.balanceInCents()
        );
    }

    public static AccountEntity entityWithCustomerId(UUID customerId) {
        return new AccountEntity(
                newAccountId().getId(),
                customerId,
                INITIAL_BALANCE_ZERO.balanceInCents()
        );
    }

    private static AccountId newAccountId() {
        return AccountId.from(UUID.randomUUID());
    }

    private static CustomerId newCustomerId() {
        return CustomerId.from(UUID.randomUUID());
    }

}
