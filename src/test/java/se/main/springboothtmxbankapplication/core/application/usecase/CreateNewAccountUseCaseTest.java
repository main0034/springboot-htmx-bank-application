package se.main.springboothtmxbankapplication.core.application.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.core.application.command.CreateNewAccountCommand;
import se.main.springboothtmxbankapplication.core.application.primitive.CreateNewAccountResult;
import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.core.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.domain.transaction.service.TransactionService;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateNewAccountUseCaseTest {

    private final CustomerService customerService = mock();
    private final AccountService accountService = mock();
    private final TransactionService transactionService = mock();

    private final CreateNewAccountUseCase useCase = new CreateNewAccountUseCase(
            customerService, accountService, transactionService
    );

    @Test
    void should_not_create_new_account_for_if_unknown_customer() {
        givenCustomerIsMissing();

        CustomerId unkownCustomer = CustomerId.from(UUID.randomUUID());
        CreateNewAccountCommand command = new CreateNewAccountCommand(
                unkownCustomer,
                Balance.from(BigInteger.ZERO)
        );

        CreateNewAccountResult response = useCase.createNewAccount(command);

        assertThat(response).isEqualTo(CreateNewAccountResult.unknownCustomer(unkownCustomer));
        verify(customerService).exists(unkownCustomer);
        verifyNoInteractions(accountService);
        verifyNoInteractions(transactionService);
    }

    @Test
    void should_create_account_without_transaction_for_existing_customer_if_balance_is_zero() {
        givenCustomerExists();

        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        Balance balanceZero = Balance.from(BigInteger.ZERO);
        AccountId accountId = AccountId.from(UUID.randomUUID());
        when(accountService.createNew(any(), any())).thenReturn(new Account(
                accountId,
                customerId,
                balanceZero
        ));

        CreateNewAccountCommand command = new CreateNewAccountCommand(
                customerId,
                balanceZero
        );

        CreateNewAccountResult response = useCase.createNewAccount(command);

        assertThat(response).isEqualTo(CreateNewAccountResult.success(accountId));
        verify(customerService).exists(customerId);
        verify(accountService).createNew(customerId, balanceZero);
        verifyNoInteractions(transactionService);
    }

    @Nested
    class CreateWithTransaction {

        @Test
        void should_create_incoming_transaction_for_if_balance_is_positive() {
            givenCustomerExists();

            CustomerId customerId = CustomerId.from(UUID.randomUUID());
            Balance balancePoistive = Balance.from(BigInteger.TEN);
            AccountId accountId = AccountId.from(UUID.randomUUID());
            when(accountService.createNew(any(), any())).thenReturn(new Account(
                    accountId,
                    customerId,
                    balancePoistive
            ));

            CreateNewAccountCommand command = new CreateNewAccountCommand(
                    customerId,
                    balancePoistive
            );

            CreateNewAccountResult response = useCase.createNewAccount(command);

            assertThat(response).isEqualTo(CreateNewAccountResult.success(accountId));
            verify(customerService).exists(customerId);
            verify(accountService).createNew(customerId, balancePoistive);
            verify(transactionService).create(accountId, TransactionType.INCOMING, Amount.from(balancePoistive.abs()));
        }

        @Test
        void should_create_outgoing_transaction_for_if_balance_is_negative() {
            givenCustomerExists();

            CustomerId customerId = CustomerId.from(UUID.randomUUID());
            Balance balancePoistive = Balance.from(BigInteger.valueOf(-10));
            AccountId accountId = AccountId.from(UUID.randomUUID());
            when(accountService.createNew(any(), any())).thenReturn(new Account(
                    accountId,
                    customerId,
                    balancePoistive
            ));

            CreateNewAccountCommand command = new CreateNewAccountCommand(
                    customerId,
                    balancePoistive
            );

            CreateNewAccountResult response = useCase.createNewAccount(command);

            assertThat(response).isEqualTo(CreateNewAccountResult.success(accountId));
            verify(customerService).exists(customerId);
            verify(accountService).createNew(customerId, balancePoistive);
            verify(transactionService).create(accountId, TransactionType.OUTGOING, Amount.from(balancePoistive.abs()));
        }
    }

    private void givenCustomerExists() {
        when(customerService.exists(any())).thenReturn(true);
    }

    private void givenCustomerIsMissing() {
        when(customerService.exists(any())).thenReturn(false);
    }

}