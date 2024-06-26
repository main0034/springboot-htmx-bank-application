package se.main.springboothtmxbankapplication.adapter.out.jpa.customer.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.out.jpa.customer.entity.CustomerEntity;
import se.main.springboothtmxbankapplication.core.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

@Component
public class CustomerEntityConverter {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
                customer.id().getId(),
                customer.name().value(),
                customer.surname().value()
        );
    }

    public Customer toDomain(CustomerEntity entity) {
        return new Customer(
                CustomerId.from(entity.getCustomerId()),
                Name.from(entity.getName()),
                Surname.from(entity.getSurname())
        );
    }
}
