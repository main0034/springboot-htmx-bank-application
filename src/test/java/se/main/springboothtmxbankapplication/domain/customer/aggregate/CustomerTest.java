package se.main.springboothtmxbankapplication.domain.customer.aggregate;


import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerTest {

    private static final CustomerId CUSTOMER_ID = CustomerId.from(UUID.randomUUID());
    private static final Name NAME = Name.from("Name1");
    private static final Surname SURNAME = Surname.from("Surname1");

    @Test
    void can_create() {
        assertThatNoException().isThrownBy(() -> new Customer(CUSTOMER_ID, NAME, SURNAME));
    }

    @Test
    void id_must_not_be_null() {
        assertThatThrownBy(() -> new Customer(null, NAME, SURNAME))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("customerId must not be null");
    }

    @Test
    void name_must_not_be_null() {
        assertThatThrownBy(() -> new Customer(CUSTOMER_ID, null, SURNAME))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("name must not be null");
    }

    @Test
    void surname_must_not_be_null() {
        assertThatThrownBy(() -> new Customer(CUSTOMER_ID, NAME, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("surname must not be null");
    }

}