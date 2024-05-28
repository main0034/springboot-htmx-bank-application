package se.main.springboothtmxbankapplication.adapter.gui.model;

import java.util.UUID;

public record CustomerRepresentation(
        String customerId,
        String name,
        String surname
) {
}
