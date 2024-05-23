package se.main.springboothtmxbankapplication.application.usecase;

import se.main.springboothtmxbankapplication.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.application.primitive.CustomerInformationResponse;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.domain.transaction.service.TransactionService;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;

public class FetchCustomerInformationUseCase {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public FetchCustomerInformationUseCase(CustomerService customerService, AccountService accountService, TransactionService transactionService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public CustomerInformationResponse getCustomerInformation(CustomerId customerId) {
        Customer customer = customerService.getById(customerId);
        List<Account> accounts = accountService.getByCustomerId(customerId);
        return new CustomerInformationResponse(
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
