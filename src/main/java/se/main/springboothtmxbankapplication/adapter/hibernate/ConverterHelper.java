package se.main.springboothtmxbankapplication.adapter.hibernate;

import java.util.Optional;
import java.util.function.Function;

public final class ConverterHelper {

    public static <V, T> T createIfExists(V value, Function<V, T> creator) {
        return Optional.ofNullable(value).map(creator).orElse(null);
    }

}
