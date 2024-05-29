package se.main.springboothtmxbankapplication.core.domain.customer.primitive;

import org.apache.commons.lang3.Validate;

public record Surname(String value) {
    public Surname {
        Validate.notNull(value, "surname-value must not be null");
        Validate.notBlank(value, "surname-value must not be blank");
        Validate.inclusiveBetween(2, 100, value.length(), "surname must be between 2 and 100 characters");
    }

    public static Surname from(String surname) {
        return new Surname(surname);
    }
}
