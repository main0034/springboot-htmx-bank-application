package se.main.springboothtmxbankapplication.adapter.hibernate.account.converter;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.AccountTestFactory;
import se.main.springboothtmxbankapplication.adapter.hibernate.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountEntityConverterTest {

    private final AccountEntityConverter converter = new AccountEntityConverter();

    @Test
    void toEntity() {
        Account account = AccountTestFactory.accountWithBalance(100);

        AccountEntity entity = converter.toEntity(account);

        assertThat(entity.getAccountId()).isEqualTo(account.id().getId());
        assertThat(entity.getCustomerId()).isEqualTo(account.customerId().getId());
        assertThat(entity.getBalanceInCents()).isEqualTo(account.balance().balanceInCents());
    }

    @Test
    void toDomain() {
        AccountEntity entity = new AccountEntity(
                UUID.randomUUID(),
                UUID.randomUUID(),
                BigInteger.ONE
        );

        Account account = converter.toDomain(entity);

        assertThat(account.id().getId()).isEqualTo(entity.getAccountId());
        assertThat(account.customerId().getId()).isEqualTo(entity.getCustomerId());
        assertThat(account.balance().balanceInCents()).isEqualTo(entity.getBalanceInCents());
    }

}