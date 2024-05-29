package se.main.springboothtmxbankapplication.adapter.in.converter;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountResponseRepresentation;
import se.main.springboothtmxbankapplication.application.primitive.CreateNewAccountResult;
import se.main.springboothtmxbankapplication.primitive.AccountId;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewAccountResultConverterTest {

    private final CreateNewAccountResultConverter converter = new CreateNewAccountResultConverter();

    @Test
    void can_convert_success_response() {
        AccountId accountId = AccountId.from(UUID.randomUUID());
        var successResponse = CreateNewAccountResult.success(accountId);

        var representation = converter.toResponseEntity(successResponse);

        assertThat(representation.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(representation.getBody()).isInstanceOf(CreateNewAccountResponseRepresentation.Success.class);

        var successRepresentation = (CreateNewAccountResponseRepresentation.Success) representation.getBody();
        assertThat(successRepresentation.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(successRepresentation.getAccountId()).isEqualTo(accountId.getId());
    }

    @Test
    void can_convert_unknown_customer_response() {
        CustomerId customerId = CustomerId.from(UUID.randomUUID());
        var successResponse = CreateNewAccountResult.unknownCustomer(customerId);

        var representation = converter.toResponseEntity(successResponse);

        assertThat(representation.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(representation.getBody()).isInstanceOf(CreateNewAccountResponseRepresentation.UnknownCustomer.class);

        var successRepresentation = (CreateNewAccountResponseRepresentation.UnknownCustomer) representation.getBody();
        assertThat(successRepresentation.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assertThat(successRepresentation.getError())
                .isEqualTo("Customer with id '%s' does not exist".formatted(customerId.getId()));
    }

}