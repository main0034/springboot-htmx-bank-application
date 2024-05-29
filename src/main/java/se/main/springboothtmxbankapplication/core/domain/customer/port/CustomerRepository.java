package se.main.springboothtmxbankapplication.domain.customer.port;

import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

public interface CustomerRepository {

    void save(Customer customer);

    Customer getById(CustomerId customerId);

    boolean exists(CustomerId customerId);

}
