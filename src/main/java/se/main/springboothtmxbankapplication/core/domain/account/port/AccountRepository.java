package se.main.springboothtmxbankapplication.core.domain.account.port;

import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

import java.util.List;

public interface AccountRepository {

    void save(Account account);

    void update(Account account);

    List<Account> getForCustomerId(CustomerId customerId);

}
