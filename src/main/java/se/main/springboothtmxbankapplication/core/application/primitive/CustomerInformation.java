package se.main.springboothtmxbankapplication.core.application.primitive;

import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Surname;

import java.util.List;

public record CustomerInformation(
        Name name,
        Surname surname,
        List<AccountInformation> accountInformation
) {
}
