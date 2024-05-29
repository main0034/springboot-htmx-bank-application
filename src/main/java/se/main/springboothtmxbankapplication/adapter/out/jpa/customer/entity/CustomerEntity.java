package se.main.springboothtmxbankapplication.adapter.out.hibernate.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(schema = "public", name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    private UUID customerId;

    private String name;

    private String surname;

}
