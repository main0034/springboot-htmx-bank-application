package se.main.springboothtmxbankapplication.primitives;

import com.google.common.base.Verify;

import java.util.UUID;

public abstract class AggregateId {
    private final UUID id;

    protected AggregateId(UUID id) {
        Verify.verifyNotNull(id, "id must not be null");
        this.id = id;
    }

    public UUID id() {
        return id;
    }

    public String toString(String className) {
        return className + "[id=" + id.toString() + "]";
    }
}
