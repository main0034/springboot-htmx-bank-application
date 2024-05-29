package se.main.springboothtmxbankapplication.core.domain.transaction.primitive;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public record Timestamp(LocalDateTime localDateTime) {

    public Timestamp {
        Validate.notNull(localDateTime, "localDateTime must not be null");
    }

    public static Timestamp from(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime);
    }

    public static Timestamp now() {
        return new Timestamp(LocalDateTime.now());
    }

}
