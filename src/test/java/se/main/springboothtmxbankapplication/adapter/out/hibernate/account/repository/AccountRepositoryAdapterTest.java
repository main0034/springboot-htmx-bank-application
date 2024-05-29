package se.main.springboothtmxbankapplication.adapter.out.hibernate.account.repository;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.AccountTestFactory;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.account.converter.AccountEntityConverter;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountRepositoryAdapterTest {

    private final AccountJpaRepository jpaRepository = mock();
    private final AccountEntityConverter converter = mock();
    private final AccountRepositoryAdapter accountRepositoryAdapter = new AccountRepositoryAdapter(
            jpaRepository, converter
    );

    @Test
    void save_calls_repository_with_converted_account() {
        AccountEntity entity = mock();
        when(converter.toEntity(any())).thenReturn(entity);

        Account account = AccountTestFactory.accountWithBalanceZero();
        accountRepositoryAdapter.save(account);

        verify(converter).toEntity(account);
        verify(jpaRepository).save(entity);
    }

    @Test
    void update_calls_repository_with_converted_account() {
        AccountEntity entity = mock();
        when(converter.toEntity(any())).thenReturn(entity);

        Account account = AccountTestFactory.accountWithBalanceZero();
        accountRepositoryAdapter.update(account);

        verify(converter).toEntity(account);
        verify(jpaRepository).save(entity);
    }

    @Test
    void getForCustomerId_calls_repository_and_converts_to_accounts() {
        AccountEntity entity1 = AccountTestFactory.entityWithBalanceZero();
        AccountEntity entity2 = AccountTestFactory.entityWithBalanceZero();
        when(jpaRepository.findByCustomerId(any())).thenReturn(List.of(entity1, entity2));

        Account account1 = mock();
        Account account2 = mock();
        when(converter.toDomain(entity1)).thenReturn(account1);
        when(converter.toDomain(entity2)).thenReturn(account2);

        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        List<Account> accounts = accountRepositoryAdapter.getForCustomerId(customerId);

        assertThat(accounts).containsExactly(account1, account2);
        verify(jpaRepository).findByCustomerId(customerId.getId());
        verify(converter).toDomain(entity1);
        verify(converter).toDomain(entity2);
    }

}