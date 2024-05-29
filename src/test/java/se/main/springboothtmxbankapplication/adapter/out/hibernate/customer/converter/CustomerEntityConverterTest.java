package se.main.springboothtmxbankapplication.adapter.out.hibernate.customer.converter;

import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.CustomerTestFactory;
import se.main.springboothtmxbankapplication.adapter.out.hibernate.customer.entity.CustomerEntity;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerEntityConverterTest {

    private final CustomerEntityConverter converter = new CustomerEntityConverter();

    @Test
    void toEntity() {
        Customer customer = CustomerTestFactory.customer();

        CustomerEntity entity = converter.toEntity(customer);

        assertThat(entity.getCustomerId()).isEqualTo(customer.id().getId());
        assertThat(entity.getName()).isEqualTo(customer.name().value());
        assertThat(entity.getSurname()).isEqualTo(customer.surname().value());
    }

    @Test
    void toDomain() {
        CustomerEntity entity = CustomerTestFactory.entity();

        Customer customer = converter.toDomain(entity);

        assertThat(customer.id().getId()).isEqualTo(entity.getCustomerId());
        assertThat(customer.name().value()).isEqualTo(entity.getName());
        assertThat(customer.surname().value()).isEqualTo(entity.getSurname());
    }

}