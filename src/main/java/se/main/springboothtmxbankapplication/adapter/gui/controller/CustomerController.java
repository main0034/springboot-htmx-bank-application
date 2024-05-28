package se.main.springboothtmxbankapplication.adapter.gui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.main.springboothtmxbankapplication.adapter.gui.model.CustomerRepresentation;
import se.main.springboothtmxbankapplication.domain.customer.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerRepresentation> getCustomers(Model model) {
        List<CustomerRepresentation> customers = customerService.getAll()
                .stream()
                .map(c -> new CustomerRepresentation(
                        c.id().getId().toString(),
                        c.name().value(),
                        c.surname().value()
                ))
                .toList();
        model.addAttribute("customers", customers);
        LOGGER.info(customers.toString());
        return customers;
    }
}
