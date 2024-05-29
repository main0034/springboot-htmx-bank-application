package se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    private int type;

    private BigInteger amount;

    private LocalDateTime timestamp;

    private String description;
}
