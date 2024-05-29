package se.main.springboothtmxbankapplication.adapter.out.jpa.customer.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.main.springboothtmxbankapplication.CustomerTestFactory;
import se.main.springboothtmxbankapplication.adapter.out.jpa.customer.converter.CustomerEntityConverter;
import se.main.springboothtmxbankapplication.adapter.out.jpa.customer.entity.CustomerEntity;
import se.main.springboothtmxbankapplication.core.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerRepositoryAdapterTest {

    private final CustomerJpaRepository jpaRepository = mock();
    private final CustomerEntityConverter converter = mock();

    private final CustomerRepositoryAdapter customerRepositoryAdapter = new CustomerRepositoryAdapter(
            jpaRepository, converter
    );

    @Test
    void save_calls_repository_with_converted_customer() {
        CustomerEntity entity = mock();
        when(converter.toEntity(any())).thenReturn(entity);

        Customer customer = CustomerTestFactory.customer();
        customerRepositoryAdapter.save(customer);

        verify(converter).toEntity(customer);
        verify(jpaRepository).save(entity);
    }

    @Test
    void getById_returns_customer_with_given_id() {
        CustomerEntity entity = mock();
        when(jpaRepository.getReferenceById(any())).thenReturn(entity);
        Customer customerConverted = CustomerTestFactory.customer();
        when(converter.toDomain(entity)).thenReturn(customerConverted);

        Customer result = customerRepositoryAdapter.getById(customerConverted.id());
        assertThat(result).isEqualTo(customerConverted);

        verify(jpaRepository).getReferenceById(customerConverted.id().getId());
        verify(converter).toDomain(entity);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void exists_returns_response_from_repository(boolean exists) {
        when(jpaRepository.existsById(any())).thenReturn(exists);

        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        boolean result = customerRepositoryAdapter.exists(customerId);

        assertThat(result).isEqualTo(exists);
        verify(jpaRepository).existsById(customerId.getId());
    }
}