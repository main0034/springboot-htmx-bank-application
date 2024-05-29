package se.main.springboothtmxbankapplication.core.application.usecase;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.main.springboothtmxbankapplication.core.application.command.CreateNewAccountCommand;
import se.main.springboothtmxbankapplication.core.application.primitive.CreateNewAccountResult;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.core.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.core.domain.transaction.primitive.TransactionType;
import se.main.springboothtmxbankapplication.core.domain.transaction.service.TransactionService;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.Amount;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.util.Optional;

@Service
public class CreateNewAccountUseCase {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public CreateNewAccountUseCase(CustomerService customerService, AccountService accountService, TransactionService transactionService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public CreateNewAccountResult createNewAccount(CreateNewAccountCommand command) {
        return customerExists(command.customerId())
                .map(customerId -> createAccount(customerId, command.initialBalance()))
                .map(accountId -> createTransactionIfBalanceIsNonZero(accountId, command.initialBalance()))
                .map(accountId -> (CreateNewAccountResult) CreateNewAccountResult.success(accountId))
                .orElseGet(() -> CreateNewAccountResult.unknownCustomer(command.customerId()));
    }

    private Optional<CustomerId> customerExists(CustomerId customerId) {
        if (customerService.exists(customerId)) {
            return Optional.of(customerId);
        }

        return Optional.empty();
    }

    private AccountId createAccount(CustomerId customerId, Balance initialBalance) {
        return accountService.createNew(customerId, initialBalance).id();
    }

    private AccountId createTransactionIfBalanceIsNonZero(AccountId accountId, Balance balance) {
        if (balance.isZero()) {
            return accountId;
        }

        TransactionType type = balance.isPositive() ? TransactionType.INCOMING : TransactionType.OUTGOING;

        Amount amountAbsoluteValue = Amount.from(balance.abs());
        transactionService.create(accountId, type, amountAbsoluteValue);
        return accountId;
    }
}
