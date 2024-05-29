package se.main.springboothtmxbankapplication.adapter;

import se.main.springboothtmxbankapplication.core.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.core.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.core.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Description;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.Timestamp;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionId;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public final class CustomerInformationTestFactory {

    public static CustomerInformation createCustomerInformationWithOneAccount() {
        AccountId accountId = AccountId.from(UUID.randomUUID());
        return new CustomerInformation(
                Name.from(UUID.randomUUID().toString()),
                Surname.from(UUID.randomUUID().toString()),
                List.of(
                        new AccountInformation(
                                accountId,
                                Balance.from(BigInteger.TEN),
                                List.of(
                                        new Transaction(
                                                TransactionId.from(UUID.randomUUID()),
                                                accountId,
                                                TransactionType.INCOMING,
                                                Amount.from(100L),
                                                Timestamp.now(),
                                                Description.from(UUID.randomUUID().toString())
                                        )
                                )
                        )
                )
        );
    }
}
