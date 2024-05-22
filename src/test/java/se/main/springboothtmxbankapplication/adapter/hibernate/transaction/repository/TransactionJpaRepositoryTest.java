package se.main.springboothtmxbankapplication.adapter.hibernate.transaction.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import se.main.springboothtmxbankapplication.TransactionTestFactory;
import se.main.springboothtmxbankapplication.adapter.hibernate.AccountTestDataHelper;
import se.main.springboothtmxbankapplication.adapter.hibernate.CustomerTestDataHelper;
import se.main.springboothtmxbankapplication.adapter.hibernate.transaction.entity.TransactionEntity;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({CustomerTestDataHelper.class, AccountTestDataHelper.class})
class TransactionJpaRepositoryTest {

    @Autowired
    private AccountTestDataHelper accountTestDataHelper;

    @Autowired
    private TransactionJpaRepository jpaRepository;

    @Test
    void findByAccountIdOrderByTimestampDescending_returns_empty_list_if_no_transaction_with_given_accountId_exists() {
        UUID accountId = accountTestDataHelper.createNewAccount();
        given10TransactionsSavedInNonChronologicalOrder(accountId);

        UUID otherAccountId = UUID.randomUUID();
        List<TransactionEntity> result = jpaRepository.findAllByAccountIdOrderByTimestampDesc(otherAccountId);
        assertThat(result).isEmpty();
    }

    @Test
    void findByAccountIdOrderByTimestampDescending_returns_ordered_transaction() {
        UUID accountId = accountTestDataHelper.createNewAccount();

        List<TransactionEntity> entities = given10TransactionsSavedInNonChronologicalOrder(accountId);

        List<UUID> accountIdsInExpectedOrder = entities.stream()
                .sorted(Comparator.comparing(TransactionEntity::getTimestamp).reversed())
                .map(TransactionEntity::getId)
                .toList();

        List<TransactionEntity> result = jpaRepository.findAllByAccountIdOrderByTimestampDesc(accountId);
        assertThat(result)
                .extracting(TransactionEntity::getId)
                .containsExactlyElementsOf(accountIdsInExpectedOrder);
    }

    private List<TransactionEntity> given10TransactionsSavedInNonChronologicalOrder(UUID accountId) {
        LocalDateTime now = LocalDateTime.now();
        return IntStream.rangeClosed(1, 10)
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(i -> TransactionTestFactory.entity(accountId, now.minusDays(i)))
                .map(jpaRepository::saveAndFlush)
                .toList();
    }

    private void saveTransactions(TransactionEntity... transactions) {
        Arrays.stream(transactions)
                .forEach(jpaRepository::saveAndFlush);
    }

}