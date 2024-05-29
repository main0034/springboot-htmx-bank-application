package se.main.springboothtmxbankapplication.core.domain.transaction.primitive;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TimestampTest {

    @Test
    void can_create() {
        LocalDateTime localDateTime = LocalDateTime.now();
        assertThatNoException().isThrownBy(() -> new Timestamp(localDateTime));
    }

    @Test
    void localDatetime_must_not_be_null() {
        assertThatThrownBy(() -> new Timestamp(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("localDateTime must not be null");
    }

}