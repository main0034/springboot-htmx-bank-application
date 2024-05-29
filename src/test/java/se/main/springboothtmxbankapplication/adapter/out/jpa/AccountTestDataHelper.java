package se.main.springboothtmxbankapplication.adapter.out.hibernate;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.account.repository.AccountJpaRepository;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class AccountTestDataHelper {

    private final CustomerTestDataHelper customerTestDataHelper;
    private final AccountJpaRepository accountRepository;

    public AccountTestDataHelper(CustomerTestDataHelper customerTestDataHelper,
                                 AccountJpaRepository accountRepository) {
        this.customerTestDataHelper = customerTestDataHelper;
        this.accountRepository = accountRepository;
    }

    public UUID createNewAccount() {
        var customerId = UUID.randomUUID();
        customerTestDataHelper.saveCustomerWithId(customerId);

        var accountId = UUID.randomUUID();
        var account = new AccountEntity(accountId, customerId, BigInteger.ZERO);
        accountRepository.save(account);

        return accountId;
    }
}
