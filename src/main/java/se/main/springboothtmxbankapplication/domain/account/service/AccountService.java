package se.main.springboothtmxbankapplication.domain.account.service;

import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.port.AccountRepository;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createNew(CustomerId customerId, Balance initialBalance) {
        Account account = new Account(AccountId.from(UUID.randomUUID()),
                customerId,
                initialBalance);

        accountRepository.save(account);

        return account;
    }

    public List<Account> getByCustomerId(CustomerId customerId) {
        return accountRepository.getForCustomerId(customerId);
    }

    public List<Account> getAll() {
        return accountRepository.getAll();
    }
}
