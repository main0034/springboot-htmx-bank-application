package se.main.springboothtmxbankapplication.adapter.in.dto;

import java.util.List;

public record CustomerInformationRepresentation(
        String name,
        String surname,
        List<AccountInformationRepresentation> accounts
) {
}
