package se.main.springboothtmxbankapplication.adapter.htmx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.main.springboothtmxbankapplication.account.command.CreateAccountCommand;

import java.math.BigInteger;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommandGateway commandHandler;

    @Test
    void GET_returns_forwarded_url_index() throws Exception {
        mvc.perform(get("/account").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("index"));
    }

    @Nested
    class PostTest {

        @Test
        void returns_forwarded_url_index() throws Exception {
            mvc.perform(post("/account")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")
                            .param("customerId", UUID.randomUUID().toString())
                            .param("initialCredit", "100"))
                    .andExpect(status().isOk())
                    .andExpect(forwardedUrl("index"));
        }

        @Test
        void calls_commandGateway() throws Exception {
            UUID customerId = UUID.randomUUID();
            long balance = 100;
            mvc.perform(post("/account")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")
                            .param("customerId", customerId.toString())
                            .param("initialCredit", String.valueOf(balance)))
                    .andExpect(status().isOk());

            verify(commandHandler).send(assertArg(c -> {
                CreateAccountCommand command = (CreateAccountCommand) c;
                assertThat(command.customerId().getId()).isEqualTo(customerId);
                assertThat(command.balance().balanceInCents()).isEqualTo(BigInteger.valueOf(balance));
            }));
        }
    }


}