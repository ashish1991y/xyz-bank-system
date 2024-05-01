package com.xyzbank.eob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currency_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
