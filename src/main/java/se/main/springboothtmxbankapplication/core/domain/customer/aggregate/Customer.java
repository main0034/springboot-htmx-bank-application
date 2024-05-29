package se.main.springboothtmxbankapplication.core.domain.customer.aggregate;

import org.apache.commons.lang3.Validate;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

public class Customer {

    private final CustomerId customerId;
    private final Name name;
    private final Surname surname;

    public Customer(CustomerId customerId, Name name, Surname surname) {
        this.customerId = Validate.notNull(customerId, "customerId must not be null");
        this.name = Validate.notNull(name, "name must not be null");
        this.surname = Validate.notNull(surname, "surname must not be null");
    }

    public CustomerId id() {
        return customerId;
    }

    public Name name() {
        return name;
    }

    public Surname surname() {
        return surname;
    }
}
