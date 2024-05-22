package se.main.springboothtmxbankapplication.domain.customer.primitive;

import org.apache.commons.lang3.Validate;

public record Name(String value) {

    public Name {
        Validate.notNull(value, "name-value must not be null");
    }

    public static Name from(String name) {
        return new Name(name);
    }
}
