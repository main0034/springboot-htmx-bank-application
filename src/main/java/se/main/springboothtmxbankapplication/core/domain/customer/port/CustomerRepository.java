package se.main.springboothtmxbankapplication.core.domain.customer.port;

import se.main.springboothtmxbankapplication.core.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

public interface CustomerRepository {

    void save(Customer customer);

    Customer getById(CustomerId customerId);

    boolean exists(CustomerId customerId);

}
