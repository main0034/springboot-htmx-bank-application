package se.main.springboothtmxbankapplication.adapter.out.jpa.transaction.repository;

import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.out.jpa.transaction.converter.TransactionEntityConverter;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.port.TransactionRepository;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;

import java.util.List;

@Repository
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;
    private final TransactionEntityConverter entityConverter;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpaRepository,
                                        TransactionEntityConverter entityConverter) {
        this.jpaRepository = jpaRepository;
        this.entityConverter = entityConverter;
    }

    @Override
    public void save(Transaction transaction) {
        var entity = entityConverter.toEntity(transaction);
        jpaRepository.save(entity);
    }

    // TODO Should be paginated
    @Override
    public List<Transaction> findByAccountIdOrderedByTimestampDescending(AccountId accountId) {
        return jpaRepository.findAllByAccountIdOrderByTimestampDesc(accountId.getId())
                .stream()
                .map(entityConverter::toDomain)
                .toList();
    }
}
