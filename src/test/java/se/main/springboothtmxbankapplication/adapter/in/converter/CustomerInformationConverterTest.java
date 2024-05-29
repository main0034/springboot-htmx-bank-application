package se.main.springboothtmxbankapplication.adapter.in.converter;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.adapter.CustomerInformationTestFactory;
import se.main.springboothtmxbankapplication.adapter.in.dto.AccountInformationRepresentation;
import se.main.springboothtmxbankapplication.adapter.in.dto.CustomerInformationRepresentation;
import se.main.springboothtmxbankapplication.adapter.in.dto.TransactionRepresentation;
import se.main.springboothtmxbankapplication.core.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.core.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Description;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerInformationConverterTest {

    private final CustomerInformationConverter converter = new CustomerInformationConverter();

    @Test
    void should_convert_name() {
        CustomerInformation customerInformation = CustomerInformationTestFactory.createCustomerInformationWithOneAccount();

        CustomerInformationRepresentation representation = converter.toRepresentation(customerInformation);

        assertThat(representation.name()).isEqualTo(customerInformation.name().value());
    }

    @Test
    void should_convert_surname() {
        CustomerInformation customerInformation = CustomerInformationTestFactory.createCustomerInformationWithOneAccount();

        CustomerInformationRepresentation representation = converter.toRepresentation(customerInformation);

        assertThat(representation.surname()).isEqualTo(customerInformation.surname().value());
    }

    @Nested
    class AccountInformationTest {

        @Test
        void should_convert_accountInformation() {
            CustomerInformation customerInformation = CustomerInformationTestFactory.createCustomerInformationWithOneAccount();

            CustomerInformationRepresentation representation = converter.toRepresentation(customerInformation);

            List<AccountInformationRepresentation> accountInformationRepresentation = representation.accounts();

            AccountInformation account = customerInformation.accountInformation().getFirst();
            assertThat(accountInformationRepresentation)
                    .hasSize(1)
                    .allSatisfy(accountRepresentation -> {
                        assertThat(accountRepresentation.accountId()).isEqualTo(account.accountId().getId());
                        assertThat(accountRepresentation.balanceInCents()).isEqualTo(account.balance().balanceInCents());
                    });
        }

        @Test
        void should_convert_transactions() {
            CustomerInformation customerInformation = CustomerInformationTestFactory.createCustomerInformationWithOneAccount();

            CustomerInformationRepresentation representation = converter.toRepresentation(customerInformation);

            List<TransactionRepresentation> transactions = representation.accounts()
                    .getFirst()
                    .transactions();

            Transaction transaction = customerInformation.accountInformation()
                    .getFirst()
                    .transactions()
                    .getFirst();

            assertThat(transactions)
                    .hasSize(1)
                    .allSatisfy(transactionRepresentation -> {
                        assertThat(transactionRepresentation.id()).isEqualTo(transaction.id().getId());
                        assertThat(transactionRepresentation.type()).isEqualTo(transaction.type().name());
                        assertThat(transactionRepresentation.amount()).isEqualTo(transaction.amount().value());
                        assertThat(transactionRepresentation.description()).isEqualTo(transaction.description()
                                .map(Description::value)
                                .orElseThrow());
                    });
        }
    }
}