package se.main.springboothtmxbankapplication.adapter.htmx.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.main.springboothtmxbankapplication.account.command.CreateAccountCommand;
import se.main.springboothtmxbankapplication.account.domain.Balance;
import se.main.springboothtmxbankapplication.adapter.htmx.request.CreateAccountRequest;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.math.BigInteger;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final CommandGateway commandGateway;

    public AccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@NotNull @Valid CreateAccountRequest createAccountRequest, Model model) {
        LOGGER.info("Request received: {}", createAccountRequest);
        commandGateway.send(new CreateAccountCommand(
                CustomerId.from(createAccountRequest.customerId()),
                Balance.from(BigInteger.valueOf(createAccountRequest.initialCredit()))
        ));

        return "index";
    }
}
