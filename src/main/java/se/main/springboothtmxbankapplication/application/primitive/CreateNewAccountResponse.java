package se.main.springboothtmxbankapplication.application.primitive;

import lombok.EqualsAndHashCode;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

public sealed interface CreateNewAccountResponse {

    record Success(AccountId accountId) implements CreateNewAccountResponse {
    }

    @EqualsAndHashCode
    final class UnknownCustomer implements CreateNewAccountResponse {

        private final String message;

        public UnknownCustomer(CustomerId customerId) {
            this.message = "The customer with id '%s' does not exist".formatted(customerId);
        }

        public String message() {
            return message;
        }
    }
}
