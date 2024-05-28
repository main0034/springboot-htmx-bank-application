package se.main.springboothtmxbankapplication.domain.customer.port;

import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;

public interface CustomerRepository {

    void save(Customer customer);

    Customer getById(CustomerId customerId);

    boolean exists(CustomerId customerId);

    List<Customer> getAll();
}
