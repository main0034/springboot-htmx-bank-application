package se.main.springboothtmxbankapplication.primitives;

import java.util.UUID;

public class CustomerId extends AggregateId {

    private CustomerId(UUID id) {
        super(id);
    }

    public static CustomerId from(UUID id) {
        return new CustomerId(id);
    }
}
