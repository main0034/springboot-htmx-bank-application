package se.main.springboothtmxbankapplication.domain.transaction.primitive;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DescriptionTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 50, 100, 199, 200})
    void can_create_with_value_of_length_1_to_200(int length) {
        String value = "v".repeat(length);
        assertThatNoException().isThrownBy(() -> new Description(value));
    }

    @Test
    void value_must_not_be_null() {
        assertThatThrownBy(() -> new Description(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("description-value must not be null");
    }

    @Test
    void value_must_be_at_least_1_character() {
        String value = "";
        assertThatThrownBy(() -> new Description(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("description must be between 1 and 200 characters");
    }

    @Test
    void value_must_not_be_blank() {
        String value = "   ";
        assertThatThrownBy(() -> new Description(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("description must not be blank");
    }
}