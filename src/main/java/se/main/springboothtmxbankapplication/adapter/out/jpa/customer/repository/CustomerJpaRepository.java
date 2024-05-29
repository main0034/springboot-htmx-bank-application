package se.main.springboothtmxbankapplication.adapter.out.jpa.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.out.jpa.customer.entity.CustomerEntity;

import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
