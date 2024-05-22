package se.main.springboothtmxbankapplication;

import se.main.springboothtmxbankapplication.adapter.hibernate.customer.entity.CustomerEntity;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

public final class CustomerTestFactory {

    public static Customer customer() {
        return new Customer(
                CustomerId.from(UUID.randomUUID()),
                Name.from(UUID.randomUUID().toString()),
                Surname.from((UUID.randomUUID().toString()))
        );
    }

    public static CustomerEntity entity() {
        return new CustomerEntity(
                UUID.randomUUID(),
                UUID.randomUUID().toString(),
                (UUID.randomUUID().toString())
        );
    }

    public static CustomerEntity entityWithId(UUID customerId) {
        return new CustomerEntity(
                customerId,
                UUID.randomUUID().toString(),
                (UUID.randomUUID().toString())
        );
    }
}
