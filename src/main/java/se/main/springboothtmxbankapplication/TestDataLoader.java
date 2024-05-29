package se.main.springboothtmxbankapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Name;
import se.main.springboothtmxbankapplication.core.domain.customer.primitive.Surname;
import se.main.springboothtmxbankapplication.core.domain.customer.service.CustomerService;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class TestDataLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataLoader.class);

    private final CustomerService customerService;

    public TestDataLoader(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Creating test data...");
        createRandomCustomers(2)
                .forEach(customerData -> customerService.create(customerData.name(), customerData.surname()));
    }

    private static List<CustomerData> createRandomCustomers(int number) {
        return IntStream.rangeClosed(1, number)
                .mapToObj(i -> new CustomerData(
                        Name.from("Name" + i),
                        Surname.from("Surname" + i)
                ))
                .toList();
    }

    private record CustomerData(Name name, Surname surname) {
    }
}