package se.main.springboothtmxbankapplication.domain.account.aggregate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.AccountTestFactory;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class AccountTest {

    private static final AccountId ACCOUNT_ID = AccountId.from(UUID.randomUUID());
    private static final CustomerId CUSTOMER_ID = CustomerId.from(UUID.randomUUID());
    private static final Balance INITIAL_BALANCE_ZERO = Balance.from(BigInteger.ZERO);

    @Test
    void can_create() {
        assertThatNoException().isThrownBy(() -> new Account(
                ACCOUNT_ID,
                CUSTOMER_ID,
                INITIAL_BALANCE_ZERO
        ));
    }

    @Test
    void accountId_must_not_be_null() {
        assertThatThrownBy(() -> new Account(
                null,
                CUSTOMER_ID,
                INITIAL_BALANCE_ZERO
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("accountId must not be null");
    }

    @Test
    void customerId_must_not_be_null() {
        assertThatThrownBy(() -> new Account(
                ACCOUNT_ID,
                null,
                INITIAL_BALANCE_ZERO
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("customerId must not be null");
    }

    @Test
    void initial_balance_must_not_be_null() {
        assertThatThrownBy(() -> new Account(
                ACCOUNT_ID,
                CUSTOMER_ID,
                null
        ))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("initialBalance must not be null");
    }

    @Test
    void deposit_adds_to_balance() {
        Account emptyAccount = AccountTestFactory.accountWithBalanceZero();

        long depositAmount = 100;
        boolean depositStatus = emptyAccount.deposit(Amount.from(depositAmount));

        assertThat(depositStatus).isTrue();

        Balance newBalance = emptyAccount.balance();
        assertThat(newBalance.balanceInCents()).isEqualTo(BigInteger.valueOf(depositAmount));
    }

    @Nested
    class WithdrawTest {

        @Test
        void subtracts_balance_and_returns_true_if_sufficient_coverage() {
            Account account = AccountTestFactory.accountWithBalance(10);

            boolean withdrawStatus = account.withdraw(Amount.from(9));

            assertThat(withdrawStatus).isTrue();

            Balance newBalance = account.balance();
            assertThat(newBalance.balanceInCents()).isEqualTo(BigInteger.ONE);
        }

        @Test
        void does_nothing_and_returns_false_if_insufficient_coverage() {
            Account emptyAccount = AccountTestFactory.accountWithBalanceZero();

            boolean withdrawStatus = emptyAccount.withdraw(Amount.from(1));

            assertThat(withdrawStatus).isFalse();

            Balance balance = emptyAccount.balance();
            assertThat(balance.balanceInCents()).isEqualTo(BigInteger.ZERO);
        }
    }

}