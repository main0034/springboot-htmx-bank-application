package se.main.springboothtmxbankapplication.core.domain.account.service;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.domain.account.port.AccountRepository;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private final AccountRepository repository = mock();
    private final AccountService accountService = new AccountService(repository);

    @Test
    void createNew_calls_repository_with_account() {
        Balance initialBalance = Balance.from(BigInteger.TEN);
        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        accountService.createNew(customerId, initialBalance);

        verify(repository).save(assertArg(account -> {
            assertThat(account.customerId()).isEqualTo(customerId);
            assertThat(account.balance()).isEqualTo(initialBalance);
        }));
    }

    @Test
    void getByCustomerId_returns_result_from_repository() {
        List<Account> accounts = mock();
        when(repository.getForCustomerId(any())).thenReturn(accounts);

        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        List<Account> result = accountService.getByCustomerId(customerId);

        assertThat(result).isEqualTo(accounts);
        verify(repository).getForCustomerId(customerId);
    }
}