package se.main.springboothtmxbankapplication.domain.account.aggregate;


import org.apache.commons.lang3.Validate;
import se.main.springboothtmxbankapplication.domain.account.primitve.AccountId;
import se.main.springboothtmxbankapplication.domain.account.primitve.Amount;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

public class Account {

    private final AccountId accountId;
    private final CustomerId customerId;
    private Balance balance;

    public Account(AccountId accountId, CustomerId customerId, Balance initialBalance) {
        this.accountId = Validate.notNull(accountId, "accountId must not be null");
        this.customerId = Validate.notNull(customerId, "customerId must not be null");
        this.balance = Validate.notNull(initialBalance, "initialBalance must not be null");
    }

    public boolean deposit(Amount amount) {
        balance = balance.add(amount);
        return true;
    }

    public boolean withdraw(Amount amount) {
        if (balance.hasCoverage(amount)) {
            balance = balance.subtract(amount);
            return true;
        }
        return false;
    }

    public AccountId accountId() {
        return accountId;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Balance balance() {
        return balance;
    }
}
