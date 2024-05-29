package se.main.springboothtmxbankapplication.adapter.in.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.in.dto.AccountInformationRepresentation;
import se.main.springboothtmxbankapplication.adapter.in.dto.CustomerInformationRepresentation;
import se.main.springboothtmxbankapplication.adapter.in.dto.TransactionRepresentation;
import se.main.springboothtmxbankapplication.core.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.core.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Description;

@Component
public class CustomerInformationConverter {

    public CustomerInformationRepresentation toRepresentation(CustomerInformation customerInformation) {
        return new CustomerInformationRepresentation(
                customerInformation.name().value(),
                customerInformation.surname().value(),
                customerInformation.accountInformation().stream().map(CustomerInformationConverter::toRepresentation).toList()
        );
    }

    private static AccountInformationRepresentation toRepresentation(AccountInformation accountInformation) {
        return new AccountInformationRepresentation(
                accountInformation.accountId().getId(),
                accountInformation.balance().balanceInCents(),
                accountInformation.transactions().stream().map(CustomerInformationConverter::toRepresentation).toList()
        );
    }

    private static TransactionRepresentation toRepresentation(Transaction transaction) {
        return new TransactionRepresentation(
                transaction.id().getId(),
                transaction.type().name(),
                transaction.amount().value(),
                transaction.timestamp().localDateTime(),
                transaction.description().map(Description::value).orElse(null)
        );
    }
}
