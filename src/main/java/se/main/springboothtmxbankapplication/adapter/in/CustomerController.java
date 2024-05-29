package se.main.springboothtmxbankapplication.adapter.in;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.main.springboothtmxbankapplication.adapter.in.converter.CustomerInformationConverter;
import se.main.springboothtmxbankapplication.adapter.in.dto.CustomerInformationRepresentation;
import se.main.springboothtmxbankapplication.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.application.usecase.FetchCustomerInformationUseCase;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final FetchCustomerInformationUseCase customerInformationUseCase;
    private final CustomerInformationConverter converter;

    public CustomerController(FetchCustomerInformationUseCase customerInformationUseCase,
                              CustomerInformationConverter converter) {
        this.customerInformationUseCase = customerInformationUseCase;
        this.converter = converter;
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInformationRepresentation customer(@PathVariable @NotNull UUID customerId) {
        CustomerInformation customerInformation = customerInformationUseCase.getCustomerInformation(CustomerId.from(customerId));
        return converter.toRepresentation(customerInformation);
    }

}
