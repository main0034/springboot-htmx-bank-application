package se.main.springboothtmxbankapplication.core.domain.customer.primitive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 50, 99, 100})
    void can_create_with_value_between_2_and_100_characters(int length) {
        String value = "v".repeat(length);
        assertThatNoException().isThrownBy(() -> new Name(value));
    }

    @Test
    void value_must_not_be_null() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("name-value must not be null");
    }

    @Test
    void value_must_not_be_blank() {
        assertThatThrownBy(() -> new Name("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name-value must not be blank");
    }

    @Test
    void value_must_have_length_of_at_least_2_characters() {
        assertThatThrownBy(() -> new Name("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name must be between 2 and 100 characters");
    }

    @Test
    void value_must_have_length_of_at_most_100_characters() {
        assertThatThrownBy(() -> new Name("1".repeat(101)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name must be between 2 and 100 characters");
    }

}