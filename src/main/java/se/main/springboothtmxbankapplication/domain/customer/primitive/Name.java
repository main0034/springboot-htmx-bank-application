package se.main.springboothtmxbankapplication.domain.customer.primitive;

import org.apache.commons.lang3.Validate;

public record Name(String value) {

    public Name {
        Validate.notNull(value, "name-value must not be null");
        Validate.notBlank(value, "name-value must not be blank");
        Validate.inclusiveBetween(2, 100, value.length(), "name must be between 2 and 100 characters");
    }

    public static Name from(String name) {
        return new Name(name);
    }
}
