package se.main.springboothtmxbankapplication.adapter.gui.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.main.springboothtmxbankapplication.adapter.gui.model.AccountRepresentation;
import se.main.springboothtmxbankapplication.adapter.gui.model.CustomerRepresentation;
import se.main.springboothtmxbankapplication.application.command.CreateNewAccountCommand;
import se.main.springboothtmxbankapplication.application.primitive.AccountInformation;
import se.main.springboothtmxbankapplication.application.primitive.CreateNewAccountResponse;
import se.main.springboothtmxbankapplication.application.primitive.CustomerInformationResponse;
import se.main.springboothtmxbankapplication.application.usecase.CreateNewAccountUseCase;
import se.main.springboothtmxbankapplication.application.usecase.FetchCustomerInformationUseCase;
import se.main.springboothtmxbankapplication.domain.account.primitve.Balance;
import se.main.springboothtmxbankapplication.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.domain.customer.aggregate.Customer;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final CreateNewAccountUseCase createNewAccountUseCase;
    private final AccountService accountService;

    public AccountController(CreateNewAccountUseCase createNewAccountUseCase,
                             AccountService accountService) {
        this.createNewAccountUseCase = createNewAccountUseCase;
        this.accountService = accountService;
    }

    @GetMapping
    public String accounts(Model model) {
        List<AccountRepresentation> customers = accountService.getAll()
                .stream()
                .map(a -> new AccountRepresentation(
                        a.id().getId().toString(),
                        a.customerId().getId().toString(),
                        a.balance().balanceInCents()
                ))
                .toList();
        model.addAttribute("customers", customers);
        LOGGER.info(customers.toString());
        return "index";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@NotNull @Valid CreateAccountRequest createAccountRequest, Model model) {
        LOGGER.info("Request received: {}", createAccountRequest);
        CreateNewAccountResponse response = createNewAccountUseCase.createNewAccount(new CreateNewAccountCommand(
                CustomerId.from(createAccountRequest.customerId()),
                Balance.from(BigInteger.valueOf(createAccountRequest.initialCredit()))
        ));

        switch (response) {
            case CreateNewAccountResponse.Success success -> {
                LOGGER.info("Account created: " + success.accountId());
            }
            case CreateNewAccountResponse.UnknownCustomer unknownCustomer -> {
                LOGGER.info("Unknown customer: " + createAccountRequest.customerId);
            }
        }

        return "index";
    }

    public record CreateAccountRequest(
            @NotNull UUID customerId,
            @NotNull Long initialCredit) {
    }
}
