package se.main.springboothtmxbankapplication.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import se.main.springboothtmxbankapplication.adapter.in.converter.CreateNewAccountResultConverter;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountRequest;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountResponseRepresentation;
import se.main.springboothtmxbankapplication.core.application.primitive.CreateNewAccountResult;
import se.main.springboothtmxbankapplication.core.application.usecase.CreateNewAccountUseCase;
import se.main.springboothtmxbankapplication.core.primitive.AccountId;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateNewAccountUseCase newAccountUseCase;

    @MockBean
    private CreateNewAccountResultConverter resultConverter;

    @Test
    void creates_new_account_on_POST() throws Exception {
        CreateNewAccountResult.Success useCaseResponse = givenUseCaseResultSuccess();
        CreateNewAccountResponseRepresentation.Success converterResponse = givenConverterResult(useCaseResponse.accountId());

        UUID customerId = UUID.randomUUID();
        long initialCredit = 100;
        CreateNewAccountRequest request = new CreateNewAccountRequest(
                customerId,
                BigInteger.valueOf(initialCredit)
        );
        mockMvc.perform(post("/v1/account").content(
                                objectMapper.writeValueAsString(request)
                        )
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(converterResponse)));

        verify(newAccountUseCase).createNewAccount(assertArg(command -> {
            assertThat(command.customerId().getId()).isEqualTo(customerId);
            assertThat(command.initialBalance().balanceInCents()).isEqualTo(BigInteger.valueOf(initialCredit));
        }));

        verify(resultConverter).toResponseEntity(useCaseResponse);
    }

    private CreateNewAccountResult.Success givenUseCaseResultSuccess() {
        AccountId accountId = AccountId.from(UUID.randomUUID());
        CreateNewAccountResult.Success useCaseResult = CreateNewAccountResult.success(accountId);
        when(newAccountUseCase.createNewAccount(any())).thenReturn(useCaseResult);
        return useCaseResult;
    }

    private CreateNewAccountResponseRepresentation.Success givenConverterResult(AccountId accountId) {
        CreateNewAccountResponseRepresentation.Success converterResponse = CreateNewAccountResponseRepresentation.success(accountId);
        when(resultConverter.toResponseEntity(any())).thenReturn(
                ResponseEntity.status(HttpStatus.CREATED).body(converterResponse)
        );

        return converterResponse;
    }

}