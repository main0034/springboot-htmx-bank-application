package se.main.springboothtmxbankapplication.domain.transaction.primitive;

import org.apache.commons.lang3.Validate;

public record Description(String value) {

    public Description {
        Validate.notNull(value, "description-value must not be null");
        Validate.inclusiveBetween(1, 200, value.length(), "description must be between 1 and 200 characters");
        Validate.notBlank(value, "description must not be blank");
    }

    public static Description from(String description) {
        return new Description(description);
    }
}
