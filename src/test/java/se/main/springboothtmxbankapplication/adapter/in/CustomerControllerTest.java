package se.main.springboothtmxbankapplication.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import se.main.springboothtmxbankapplication.adapter.CustomerInformationTestFactory;
import se.main.springboothtmxbankapplication.adapter.in.converter.CustomerInformationConverter;
import se.main.springboothtmxbankapplication.adapter.in.dto.CustomerInformationRepresentation;
import se.main.springboothtmxbankapplication.application.primitive.CustomerInformation;
import se.main.springboothtmxbankapplication.application.usecase.FetchCustomerInformationUseCase;
import se.main.springboothtmxbankapplication.primitive.CustomerId;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FetchCustomerInformationUseCase fetchCustomerInformationUseCase;

    @MockBean
    private CustomerInformationConverter converter;

    @Test
    void returns_customerinformation_when_GET_with_id() throws Exception {
        CustomerInformation customerInformation = givenCustomerInformation();
        CustomerInformationRepresentation customerRepresentation = givenConverterRepresentation();

        UUID customerId = UUID.randomUUID();
        mockMvc.perform(get("/v1/customer/%s".formatted(customerId.toString())))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(customerRepresentation)));

        verify(fetchCustomerInformationUseCase).getCustomerInformation(CustomerId.from(customerId));
        verify(converter).toRepresentation(customerInformation);
    }

    private CustomerInformation givenCustomerInformation() {
        var customerInformation = CustomerInformationTestFactory.createCustomerInformationWithOneAccount();
        when(fetchCustomerInformationUseCase.getCustomerInformation(any())).thenReturn(customerInformation);
        return customerInformation;
    }

    private CustomerInformationRepresentation givenConverterRepresentation() {
        var representation = new CustomerInformationRepresentation(
                "Name",
                "Surname",
                List.of()
        );
        when(converter.toRepresentation(any())).thenReturn(representation);
        return representation;
    }

}