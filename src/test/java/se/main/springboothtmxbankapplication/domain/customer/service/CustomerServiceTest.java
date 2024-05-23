package se.main.springboothtmxbankapplication.domain.customer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.main.springboothtmxbankapplication.CustomerTestFactory;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.port.CustomerRepository;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private final CustomerRepository repository = mock();
    private final CustomerService customerService = new CustomerService(repository);

    @Test
    void create_calls_repository() {
        Name name = Name.from("Name");
        Surname surname = Surname.from("Surname");

        customerService.create(name, surname);

        verify(repository).save(assertArg(customer -> {
            assertThat(customer.name()).isEqualTo(name);
            assertThat(customer.surname()).isEqualTo(surname);
        }));
    }

    @Test
    void getById_returns_response_from_respository() {
        Customer customer = CustomerTestFactory.customer();
        when(repository.getById(any())).thenReturn(customer);

        Customer result = customerService.getById(customer.id());

        assertThat(result).isEqualTo(customer);
        verify(repository).getById(customer.id());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void exists_returns_answer_from_repository(boolean exists) {
        CustomerId customerId = mock();
        when(repository.exists(any())).thenReturn(exists);

        boolean result = customerService.exists(customerId);

        assertThat(result).isEqualTo(exists);
        verify(repository).exists(customerId);
    }
}