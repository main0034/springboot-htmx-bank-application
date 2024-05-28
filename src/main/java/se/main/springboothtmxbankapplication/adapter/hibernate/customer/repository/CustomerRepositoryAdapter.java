package se.main.springboothtmxbankapplication.adapter.hibernate.customer.repository;


import org.springframework.stereotype.Repository;
import se.main.springboothtmxbankapplication.adapter.hibernate.customer.converter.CustomerEntityConverter;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.port.CustomerRepository;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;

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

    @Override
    public List<Customer> getAll() {
        return jpaRepository.findAll()
                .stream()
                .map(converter::toDomain)
                .toList();
    }
}
