package com.xyzbank.eob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_status_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatus extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
