package se.main.springboothtmxbankapplication.account.domain;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.main.springboothtmxbankapplication.account.command.CreateAccountCommand;
import se.main.springboothtmxbankapplication.account.event.AccountCreatedEvent;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

import static org.axonframework.test.matchers.Matchers.*;

class AccountTest {

    private FixtureConfiguration<Account> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    void should_emit_event_when_create_new_account_command() {
        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        Balance balance = new Balance(BigInteger.ZERO);
        fixture.given()
                .when(new CreateAccountCommand(
                        customerId,
                        balance)
                )
                .expectEventsMatching(sequenceOf(
                        messageWithPayload(predicate(verifyAccountCreatedEvent(customerId, balance))
                        )));
    }

    private static Predicate<AccountCreatedEvent> verifyAccountCreatedEvent(CustomerId customerId, Balance balance) {
        return event -> Objects.nonNull(event) &&
                customerId.equals(event.customerId()) &&
                balance.equals(event.balance());
    }

}