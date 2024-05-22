package se.main.springboothtmxbankapplication.adapter.hibernate.account.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import se.main.springboothtmxbankapplication.AccountTestFactory;
import se.main.springboothtmxbankapplication.adapter.hibernate.CustomerTestDataHelper;
import se.main.springboothtmxbankapplication.adapter.hibernate.account.entity.AccountEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CustomerTestDataHelper.class)
class AccountJpaRepositoryTest {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerTestDataHelper customerTestDataHelper;

    @Test
    void findByCustomerId_returns_all_accounts_for_customer() {
        UUID customerId = UUID.randomUUID();
        List<UUID> existingAccounts = givenAccountsForCustomer(customerId, 2);

        List<AccountEntity> accounts = accountJpaRepository.findByCustomerId(customerId);
        assertThat(accounts)
                .extracting(AccountEntity::getAccountId)
                .containsExactlyElementsOf(existingAccounts);
    }

    @Test
    void findByCustomerId_returns_empty_list_when_no_account_exists_for_customer() {
        // Three accounts for different customers
        givenAccountsForCustomer(UUID.randomUUID(), 1);
        givenAccountsForCustomer(UUID.randomUUID(), 1);
        givenAccountsForCustomer(UUID.randomUUID(), 1);

        UUID otherCustomerId = UUID.randomUUID();
        List<AccountEntity> accounts = accountJpaRepository.findByCustomerId(otherCustomerId);
        assertThat(accounts).isEmpty();
    }

    private List<UUID> givenAccountsForCustomer(UUID customerId, int nrOfAccounts) {
        saveCustomerWithId(customerId);
        return IntStream.range(0, nrOfAccounts)
                .mapToObj(i ->
                        accountJpaRepository.save(AccountTestFactory.entityWithCustomerId(customerId))
                )
                .map(AccountEntity::getAccountId)
                .toList();
    }

    private void saveCustomerWithId(UUID customerId) {
        customerTestDataHelper.saveCustomerWithId(customerId);
    }

}