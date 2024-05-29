package se.main.springboothtmxbankapplication.primitive;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AggregateIdTest {

    @Test
    void id_must_not_be_null() {
        assertThatThrownBy(() -> new AggregateIdTestImpl(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("id must not be null");
    }

    @Test
    void toString_with_className_follows_record_style_implementation() {
        UUID id = UUID.randomUUID();
        var aggregateId = new AggregateIdTestImpl(id);
        assertThat(aggregateId.toString("TestImpl"))
                .isEqualTo("TestImpl[id=" + id + "]");
    }

    @Test
    void equals_is_true_if_different_objects_but_same_id() {
        UUID id = UUID.randomUUID();
        var aggregateId1 = new AggregateIdTestImpl(id);
        var aggregateId2 = new AggregateIdTestImpl(id);

        assertThat(aggregateId1).isEqualTo(aggregateId2);
    }

    @Test
    void equals_is_false_if_different_objects_and_different_id() {
        var aggregateId1 = new AggregateIdTestImpl(UUID.randomUUID());
        var aggregateId2 = new AggregateIdTestImpl(UUID.randomUUID());

        assertThat(aggregateId1).isNotEqualTo(aggregateId2);
    }

    private static class AggregateIdTestImpl extends AggregateId {
        AggregateIdTestImpl(UUID id) {
            super(id);
        }
    }
}