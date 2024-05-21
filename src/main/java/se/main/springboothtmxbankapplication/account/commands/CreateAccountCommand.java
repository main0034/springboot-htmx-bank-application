package se.main.springboothtmxbankapplication.account.commands;

import com.google.common.base.Verify;
import se.main.springboothtmxbankapplication.account.domain.Balance;
import se.main.springboothtmxbankapplication.primitives.CustomerId;

public record CreateAccountCommand(
        CustomerId customerId,
        Balance balance
) {

    public CreateAccountCommand {
        Verify.verifyNotNull(customerId, "customerId must not be null");
        Verify.verifyNotNull(balance, "balance must not be null");
    }
}
