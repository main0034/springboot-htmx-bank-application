package se.main.springboothtmxbankapplication.application.primitive;

import se.main.springboothtmxbankapplication.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.domain.customer.primitive.Surname;

import java.util.List;

public record CustomerInformation(
        Name name,
        Surname surname,
        List<AccountInformation> accountInformation
) {
}
