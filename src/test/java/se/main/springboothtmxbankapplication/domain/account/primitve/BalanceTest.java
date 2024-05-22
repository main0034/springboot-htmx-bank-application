package se.main.springboothtmxbankapplication.domain.account.primitve;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BalanceTest {

    @Test
    void can_create() {
        assertThatNoException().isThrownBy(() -> new Balance(BigInteger.ZERO));
    }

    @Test
    void balance_must_not_be_null() {
        assertThatThrownBy(() -> new Balance(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("balanceInCents must not not be null");
    }

    @ParameterizedTest
    @MethodSource("addTestValues")
    void add_returns_new_balance_with_current_balance_plus_value_to_be_added(long startBalance,
                                                                             long amountToAdd,
                                                                             long expectedNewBalance
    ) {
        Balance balance = Balance.from(BigInteger.valueOf(startBalance));

        Balance newBalance = balance.add(Amount.from(amountToAdd));

        assertThat(newBalance.balanceInCents()).isEqualTo(BigInteger.valueOf(expectedNewBalance));
        // balance is not modified
        assertThat(balance.balanceInCents()).isEqualTo(BigInteger.valueOf(startBalance));
    }

    static Stream<Arguments> addTestValues() {
        return Stream.of(
                Arguments.of(0L, 2L, 2L),
                Arguments.of(5L, 10L, 15L),
                Arguments.of(1_000_000_000L, 2L, 1_000_000_002L)
        );
    }


    @ParameterizedTest
    @MethodSource("subtractTestValues")
    void returns_new_balance_with_current_balance_minus_value_to_be_subtracted(long startBalance,
                                                                               long amountToSubtract,
                                                                               long expectedNewBalance
    ) {
        Balance balance = Balance.from(BigInteger.valueOf(startBalance));

        Balance newBalance = balance.subtract(Amount.from(amountToSubtract));

        assertThat(newBalance.balanceInCents()).isEqualTo(BigInteger.valueOf(expectedNewBalance));
        // balance is not modified
        assertThat(balance.balanceInCents()).isEqualTo(BigInteger.valueOf(startBalance));
    }

    static Stream<Arguments> subtractTestValues() {
        return Stream.of(
                Arguments.of(0L, 2L, -2L),
                Arguments.of(5L, 10L, -5L),
                Arguments.of(1_000_000_000L, 2L, 999_999_998L)
        );
    }

    @Nested
    class HasCoverageTest {

        @Test
        void is_true_when_balance_is_larger_than_amount() {
            Balance balance = Balance.from(BigInteger.TEN);

            Amount amount = Amount.from(9);

            assertThat(balance.hasCoverage(amount)).isTrue();
        }

        @Test
        void is_true_when_balance_is_equal_to_amount() {
            Balance balance = Balance.from(BigInteger.TEN);

            Amount amount = Amount.from(10);

            assertThat(balance.hasCoverage(amount)).isTrue();
        }

        @Test
        void is_false_when_balance_is_less_than_amount() {
            Balance balance = Balance.from(BigInteger.TEN);

            Amount amount = Amount.from(11);

            assertThat(balance.hasCoverage(amount)).isFalse();
        }
    }
}