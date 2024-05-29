package se.main.springboothtmxbankapplication.adapter.out.jpa.account.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.out.jpa.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.core.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.core.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;
import se.main.springboothtmxbankapplication.core.primitive.CustomerId;

@Component
public class AccountEntityConverter {

    public AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.id().getId(),
                account.customerId().getId(),
                account.balance().balanceInCents()
        );
    }

    public Account toDomain(AccountEntity account) {
        return new Account(
                AccountId.from(account.getAccountId()),
                CustomerId.from(account.getCustomerId()),
                Balance.from(account.getBalanceInCents())
        );
    }
}
