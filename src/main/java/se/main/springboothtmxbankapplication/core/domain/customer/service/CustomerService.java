package se.main.springboothtmxbankapplication.domain.customer.service;

import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.port.CustomerRepository;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void create(Name name, Surname surname) {
        customerRepository.save(new Customer(
                CustomerId.from(UUID.randomUUID()),
                name,
                surname
        ));
    }

    public Customer getById(CustomerId customerId) {
        return customerRepository.getById(customerId);
    }

    public boolean exists(CustomerId customerId) {
        return customerRepository.exists(customerId);
    }
}
