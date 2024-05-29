package se.main.springboothtmxbankapplication.adapter.in.converter;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import se.main.springboothtmxbankapplication.adapter.in.dto.CreateNewAccountResponseRepresentation;
import se.main.springboothtmxbankapplication.application.primitive.CreateNewAccountResult;

@Component
public class CreateNewAccountResultConverter {

    public ResponseEntity<CreateNewAccountResponseRepresentation> toResponseEntity(CreateNewAccountResult response) {
        return switch (response) {
            case CreateNewAccountResult.Success success ->
                    toEntity(CreateNewAccountResponseRepresentation.success(success.accountId()));
            case CreateNewAccountResult.UnknownCustomer unknownCustomer ->
                    toEntity(CreateNewAccountResponseRepresentation.unknownCustomer(unknownCustomer.customerId()));
        };
    }

    private static ResponseEntity<CreateNewAccountResponseRepresentation> toEntity(CreateNewAccountResponseRepresentation representation) {
        return ResponseEntity
                .status(representation.getStatus())
                .body(representation);
    }
}
