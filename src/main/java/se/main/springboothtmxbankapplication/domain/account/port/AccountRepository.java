package se.main.springboothtmxbankapplication.domain.account.port;

import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;

public interface AccountRepository {

    void save(Account account);

    void update(Account account);

    List<Account> getForCustomerId(CustomerId customerId);

    List<Account> getAll();
}
