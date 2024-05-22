package se.main.springboothtmxbankapplication;

import se.main.springboothtmxbankapplication.adapter.hibernate.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.primitve.AccountId;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

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
