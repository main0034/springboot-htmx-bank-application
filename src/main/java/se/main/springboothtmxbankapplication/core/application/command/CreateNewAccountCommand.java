package se.main.springboothtmxbankapplication.application.command;

import org.apache.commons.lang3.Validate;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

public record CreateNewAccountCommand(
        CustomerId customerId,
        Balance initialBalance
) {

    public CreateNewAccountCommand {
        Validate.notNull(customerId, "customerId must not be null");
        Validate.notNull(initialBalance, "initialBalance must not be null");
    }
}
