package se.main.springboothtmxbankapplication.adapter.out.hibernate.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.account.entity.AccountEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

    List<AccountEntity> findByCustomerId(UUID customerId);

}
