package se.main.springboothtmxbankapplication.adapter.out.hibernate.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "balance_in_cents")
    private BigInteger balanceInCents;
}
