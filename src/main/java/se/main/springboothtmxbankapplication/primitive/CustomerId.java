package se.main.springboothtmxbankapplication.primitive;

import java.io.Serializable;
import java.util.UUID;

public class CustomerId extends AggregateId implements Serializable {

    private CustomerId(UUID id) {
        super(id);
    }

    public static CustomerId from(UUID id) {
        return new CustomerId(id);
    }
}
