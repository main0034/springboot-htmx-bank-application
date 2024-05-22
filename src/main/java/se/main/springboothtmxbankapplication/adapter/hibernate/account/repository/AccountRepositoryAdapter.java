package se.main.springboothtmxbankapplication.adapter.hibernate.account.repository;

import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.hibernate.account.converter.AccountEntityConverter;
import se.main.springboothtmxbankapplication.domain.account.aggregate.Account;
import se.main.springboothtmxbankapplication.domain.port.AccountRepository;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;

@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository jpaRepository;
    private final AccountEntityConverter converter;

    public AccountRepositoryAdapter(AccountJpaRepository jpaRepository, AccountEntityConverter converter) {
        this.jpaRepository = jpaRepository;
        this.converter = converter;
    }

    @Override
    public void save(Account account) {
        var entity = converter.toEntity(account);
        jpaRepository.save(entity);
    }

    @Override
    public void update(Account account) {
        var entity = converter.toEntity(account);
        jpaRepository.save(entity);
    }

    @Override
    public List<Account> getForCustomerId(CustomerId customerId) {
        return jpaRepository.findByCustomerId(customerId.getId())
                .stream()
                .map(converter::toDomain)
                .toList();
    }
}
