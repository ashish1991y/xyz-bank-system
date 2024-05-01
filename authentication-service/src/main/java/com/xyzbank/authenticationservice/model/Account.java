package com.xyzbank.authenticationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "account_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ibanNumber;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "accountTypeId")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "currencyId")
    private Currency currency;


    @ManyToMany
    @JoinTable(
            name = "account_document_mapping",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private Set<Document> documents;
}
