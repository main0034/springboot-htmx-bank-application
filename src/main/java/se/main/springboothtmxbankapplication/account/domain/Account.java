package se.main.springboothtmxbankapplication.account.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.main.springboothtmxbankapplication.account.command.CreateAccountCommand;
import se.main.springboothtmxbankapplication.account.event.AccountCreatedEvent;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;


@Aggregate
public class Account {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @AggregateIdentifier
    private AccountId accountId;

    private CustomerId customerId;

    private Balance balance;

    @SuppressWarnings("unused")
    private Account() {
        // Required by the framework
    }

    @CommandHandler
    public Account(CreateAccountCommand command) {
        AccountId id = AccountId.from(UUID.randomUUID());
        logger.info("Create account {}: {}", id.getId(), command);
        AggregateLifecycle.apply(new AccountCreatedEvent(id, command.customerId(), command.balance()));
    }

    @EventSourcingHandler
    private void on(AccountCreatedEvent event) {
        logger.info("Account created event: {}", event);
        this.accountId = event.accountId();
        this.customerId = event.customerId();
        this.balance = event.balance();
        // TODO Create transaction if balence != 0 (here or in constructor?)
    }

}
