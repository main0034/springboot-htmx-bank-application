package se.main.springboothtmxbankapplication.adapter.gui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.main.springboothtmxbankapplication.adapter.gui.model.AccountRepresentation;
import se.main.springboothtmxbankapplication.adapter.gui.model.CustomerRepresentation;
import se.main.springboothtmxbankapplication.domain.account.service.AccountService;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/")
public class ApplicationController {

    private final CustomerService customerService;
    private final AccountService accountService;

    public ApplicationController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", customers());
        model.addAttribute("accounts", accounts());
        return "index";
    }

    private List<CustomerRepresentation> customers() {
        return customerService.getAll()
                .stream()
                .map(c -> new CustomerRepresentation(
                        c.id().getId().toString(),
                        c.name().value(),
                        c.surname().value()
                ))
                .toList();
    }

    private List<AccountRepresentation> accounts() {
        return accountService.getAll()
                .stream()
                .map(a -> new AccountRepresentation(
                        a.id().getId().toString(),
                        a.customerId().getId().toString(),
                        a.balance().balanceInCents()
                ))
                .toList();
    }

}
