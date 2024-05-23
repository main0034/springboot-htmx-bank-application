package se.main.springboothtmxbankapplication.application.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.application.command.CreateNewAccountCommand;
import se.main.springboothtmxbankapplication.application.primitive.CreateNewAccountResponse;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.domain.transaction.service.TransactionService;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.Amount;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

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
        when(customerService.exists(any())).thenReturn(false);

        CustomerId unkownCustomer = CustomerId.from(UUID.randomUUID());
        CreateNewAccountCommand command = new CreateNewAccountCommand(
                unkownCustomer,
                Balance.from(BigInteger.ZERO)
        );

        CreateNewAccountResponse response = useCase.createNewAccount(command);

        assertThat(response).isEqualTo(new CreateNewAccountResponse.UnknownCustomer(unkownCustomer));
        verify(customerService).exists(unkownCustomer);
        verifyNoInteractions(accountService);
        verifyNoInteractions(transactionService);
    }

    @Test
    void should_create_account_without_transaction_for_existing_customer_if_balance_is_zero() {
        when(customerService.exists(any())).thenReturn(true);

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

        CreateNewAccountResponse response = useCase.createNewAccount(command);

        assertThat(response).isEqualTo(new CreateNewAccountResponse.Success(accountId));
        verify(customerService).exists(customerId);
        verify(accountService).createNew(customerId, balanceZero);
        verifyNoInteractions(transactionService);
    }

    @Nested
    class CreateWithTransaction {

        @Test
        void should_create_incoming_transaction_for_if_balance_is_positive() {
            when(customerService.exists(any())).thenReturn(true);

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

            CreateNewAccountResponse response = useCase.createNewAccount(command);

            assertThat(response).isEqualTo(new CreateNewAccountResponse.Success(accountId));
            verify(customerService).exists(customerId);
            verify(accountService).createNew(customerId, balancePoistive);
            verify(transactionService).createNew(accountId, TransactionType.INCOMING, Amount.from(balancePoistive.abs()));
        }

        @Test
        void should_create_outgoing_transaction_for_if_balance_is_negative() {
            when(customerService.exists(any())).thenReturn(true);

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

            CreateNewAccountResponse response = useCase.createNewAccount(command);

            assertThat(response).isEqualTo(new CreateNewAccountResponse.Success(accountId));
            verify(customerService).exists(customerId);
            verify(accountService).createNew(customerId, balancePoistive);
            verify(transactionService).createNew(accountId, TransactionType.OUTGOING, Amount.from(balancePoistive.abs()));
        }
    }

}