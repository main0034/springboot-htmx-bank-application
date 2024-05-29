package se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findAllByAccountIdOrderByTimestampDesc(UUID accountId);

}
