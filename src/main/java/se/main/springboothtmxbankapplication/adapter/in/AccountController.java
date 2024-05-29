package se.main.springboothtmxbankapplication.adapter.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.main.springboothtmxbankapplication.adapter.in.converter.CreateNewAccountResultConverter;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountRequest;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountResponseRepresentation;
import se.main.springboothtmxbankapplication.application.command.CreateNewAccountCommand;
import se.main.springboothtmxbankapplication.application.primitive.CreateNewAccountResult;
import se.main.springboothtmxbankapplication.application.usecase.CreateNewAccountUseCase;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.primitive.CustomerId;


@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final CreateNewAccountUseCase createAccountUseCase;
    private final CreateNewAccountResultConverter responseConverter;

    public AccountController(CreateNewAccountUseCase createAccountUseCase, CreateNewAccountResultConverter responseConverter) {
        this.createAccountUseCase = createAccountUseCase;
        this.responseConverter = responseConverter;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateNewAccountResponseRepresentation> createAccount(@RequestBody @NotNull @Valid CreateNewAccountRequest request) {
        CreateNewAccountCommand command = new CreateNewAccountCommand(
                CustomerId.from(request.customerId()),
                Balance.from(request.initialCreditInCents())
        );

        CreateNewAccountResult createNewAccountResult = createAccountUseCase.createNewAccount(
                command
        );

        return responseConverter.toResponseEntity(createNewAccountResult);
    }


}
