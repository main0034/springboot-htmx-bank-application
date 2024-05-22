package se.main.springboothtmxbankapplication.adapter.hibernate;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.CustomerTestFactory;
import se.main.springboothtmxbankapplication.adapter.hibernate.customer.repository.CustomerJpaRepository;

import java.util.UUID;

@Component
public class CustomerTestDataHelper {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerTestDataHelper(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    public void saveCustomerWithId(UUID customerId) {
        var entity = CustomerTestFactory.entityWithId(customerId);
        customerJpaRepository.saveAndFlush(entity);
    }

}
