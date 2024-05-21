package se.main.springboothtmxbankapplication.primitives;

import com.google.common.base.VerifyException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AggregateIdTest {

    @Test
    void id_must_not_be_null() {
        assertThatThrownBy(() -> new AggregateIdTestImpl(null))
                .isInstanceOf(VerifyException.class)
                .hasMessage("id must not be null");
    }

    @Test
    void toString_with_className_follows_record_style_implementation() {
        UUID id = UUID.randomUUID();
        var aggregateId = new AggregateIdTestImpl(id);
        assertThat(aggregateId.toString("TestImpl"))
                .isEqualTo("TestImpl[id=" + id + "]");
    }

    private static class AggregateIdTestImpl extends AggregateId {
        AggregateIdTestImpl(UUID id) {
            super(id);
        }
    }
}