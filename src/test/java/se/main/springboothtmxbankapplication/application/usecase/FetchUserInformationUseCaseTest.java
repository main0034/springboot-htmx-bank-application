package se.main.springboothtmxbankapplication.application.usecase;

import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.AccountTestFactory;
import se.main.springboothtmxbankapplication.CustomerTestFactory;
import se.main.springboothtmxbankapplication.TransactionTestFactory;
import se.main.springboothtmxbankapplication.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.domain.transaction.aggregate.Transaction;
import se.main.springboothtmxbankapplication.domain.transaction.service.TransactionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FetchUserInformationUseCaseTest {

    private final CustomerService customerService = mock();
    private final AccountService accountService = mock();
    private final TransactionService transactionService = mock();

    private final FetchCustomerInformationUseCase useCase = new FetchCustomerInformationUseCase(
            customerService, accountService, transactionService
    );

    @Test
    void should_return_response_with_customer_and_account_information() {
        Customer customer = givenCustomer();
        Account account = givenAccount();
        List<Transaction> transactions = givenTransactions();

        CustomerInformation response = useCase.getCustomerInformation(customer.id());

        assertThat(response.name()).isEqualTo(customer.name());
        assertThat(response.surname()).isEqualTo(customer.surname());
        assertThat(response.accountInformation())
                .hasSize(1)
                .allSatisfy(accountInformation -> {
                    assertThat(accountInformation.accountId()).isEqualTo(account.id());
                    assertThat(accountInformation.balance()).isEqualTo(account.balance());
                    assertThat(accountInformation.transactions()).isEqualTo(transactions);
                });
    }

    private Customer givenCustomer() {
        var customer = CustomerTestFactory.customer();
        when(customerService.getById(any())).thenReturn(customer);
        return customer;
    }

    private Account givenAccount() {
        Account account = AccountTestFactory.accountWithBalanceZero();
        when(accountService.getByCustomerId(any())).thenReturn(List.of(account));
        return account;
    }

    private List<Transaction> givenTransactions() {
        List<Transaction> transactions = List.of(TransactionTestFactory.transaction());
        when(transactionService.getByAccountIdOrderedByTimestampDescending(any())).thenReturn(transactions);
        return transactions;
    }

}