package com.xyzbank.metadataservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency_master")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyMaster extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public CurrencyMaster(String name) {
        this.name = name;
    }
}
