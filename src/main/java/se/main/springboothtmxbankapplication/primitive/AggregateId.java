package se.main.springboothtmxbankapplication.primitive;

import com.google.common.base.Verify;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class AggregateId {

    private final UUID id;

    protected AggregateId(UUID id) {
        Verify.verifyNotNull(id, "id must not be null");
        this.id = id;
    }

    public String toString(String className) {
        return className + "[id=" + id.toString() + "]";
    }
}
