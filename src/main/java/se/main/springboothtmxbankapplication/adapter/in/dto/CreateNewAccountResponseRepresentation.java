package se.main.springboothtmxbankapplication.adapter.in.dto;

import org.springframework.http.HttpStatus;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

public sealed interface CreateNewAccountResponseRepresentation {

    int getStatus();

    final class Success implements CreateNewAccountResponseRepresentation {

        private final int status;
        private final UUID accountId;

        public Success(UUID accountId) {
            this.status = HttpStatus.CREATED.value();
            this.accountId = accountId;
        }

        public int getStatus() {
            return status;
        }

        public UUID getAccountId() {
            return accountId;
        }
    }

    final class UnknownCustomer implements CreateNewAccountResponseRepresentation {

        private final int status;
        private final String error;

        public UnknownCustomer(UUID customerId) {
            this.status = HttpStatus.UNPROCESSABLE_ENTITY.value();
            this.error = "Customer with id '%s' does not exist".formatted(customerId);
        }

        @Override
        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }
    }

    static Success success(AccountId accountId) {
        return new Success(accountId.getId());
    }

    static UnknownCustomer unknownCustomer(CustomerId customerId) {
        return new UnknownCustomer(customerId.getId());
    }

}
