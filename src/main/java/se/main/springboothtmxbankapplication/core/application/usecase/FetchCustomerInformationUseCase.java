package se.main.springboothtmxbankapplication.core.application.usecase;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.core.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.core.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.core.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.core.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.core.domain.transaction.service.TransactionService;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.util.List;

@Component
public class FetchCustomerInformationUseCase {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public FetchCustomerInformationUseCase(CustomerService customerService, AccountService accountService, TransactionService transactionService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public CustomerInformation getCustomerInformation(CustomerId customerId) {
        Customer customer = customerService.getById(customerId);
        List<Account> accounts = accountService.getByCustomerId(customerId);
        return new CustomerInformation(
                customer.name(),
                customer.surname(),
                withTransactions(accounts)
        );
    }

    private List<AccountInformation> withTransactions(List<Account> accounts) {
        return accounts.stream()
                .map(account -> new AccountInformation(
                        account.id(),
                        account.balance(),
                        transactionService.getByAccountIdOrderedByTimestampDescending(account.id())
                ))
                .toList();
    }

}
