package se.main.springboothtmxbankapplication.core.primitive;

import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class AggregateId {

    private final UUID id;

    protected AggregateId(UUID id) {
        Validate.notNull(id, "id must not be null");
        this.id = id;
    }

    public String toString(String className) {
        return className + "[id=" + id.toString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof AggregateId aggregateId) {
            return Objects.equals(aggregateId.id, id);
        }

        return false;
    }
}
