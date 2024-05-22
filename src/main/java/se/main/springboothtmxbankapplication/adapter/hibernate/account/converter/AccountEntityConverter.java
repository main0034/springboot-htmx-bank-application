package se.main.springboothtmxbankapplication.adapter.hibernate.account.converter;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.hibernate.account.entity.AccountEntity;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.account.primitve.AccountId;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

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
