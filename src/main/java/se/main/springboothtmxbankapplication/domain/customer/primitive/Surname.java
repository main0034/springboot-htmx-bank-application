package se.main.springboothtmxbankapplication.domain.customer.primitive;

import org.apache.commons.lang3.Validate;

public record Surname(String value) {
    public Surname {
        Validate.notNull(value, "surname-value must not be null");
    }

    public static Surname from(String surname) {
        return new Surname(surname);
    }
}
