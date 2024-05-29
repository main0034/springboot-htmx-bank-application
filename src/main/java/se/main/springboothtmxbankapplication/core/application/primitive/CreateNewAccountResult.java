package se.main.springboothtmxbankapplication.core.application.primitive;

import lombok.EqualsAndHashCode;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

public sealed interface CreateNewAccountResult {

    @EqualsAndHashCode
    final class Success implements CreateNewAccountResult {

        private final AccountId accountId;

        private Success(AccountId accountId) {
            this.accountId = accountId;
        }

        public AccountId accountId() {
            return accountId;
        }
    }

    @EqualsAndHashCode
    final class UnknownCustomer implements CreateNewAccountResult {

        private final CustomerId customerId;

        private UnknownCustomer(CustomerId customerId) {
            this.customerId = customerId;
        }

        public CustomerId customerId() {
            return customerId;
        }
    }

    static Success success(AccountId accountId) {
        return new Success(accountId);
    }

    static UnknownCustomer unknownCustomer(CustomerId customerId) {
        return new UnknownCustomer(customerId);
    }
}
