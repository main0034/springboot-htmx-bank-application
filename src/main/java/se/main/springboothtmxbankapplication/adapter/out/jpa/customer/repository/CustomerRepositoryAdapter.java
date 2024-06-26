package se.main.springboothtmxbankapplication.adapter.out.jpa.customer.repository;


import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.out.jpa.customer.converter.CustomerEntityConverter;
import se.main.springboothtmxbankapplication.core.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.core.domain.customer.port.CustomerRepository;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;
    private final CustomerEntityConverter converter;

    public CustomerRepositoryAdapter(CustomerJpaRepository jpaRepository, CustomerEntityConverter converter) {
        this.jpaRepository = jpaRepository;
        this.converter = converter;
    }

    @Override
    public void save(Customer customer) {
        var entity = converter.toEntity(customer);
        jpaRepository.save(entity);
    }

    @Override
    public Customer getById(CustomerId customerId) {
        var entity = jpaRepository.getReferenceById(customerId.getId());
        return converter.toDomain(entity);
    }

    @Override
    public boolean exists(CustomerId customerId) {
        return jpaRepository.existsById(customerId.getId());
    }
}
