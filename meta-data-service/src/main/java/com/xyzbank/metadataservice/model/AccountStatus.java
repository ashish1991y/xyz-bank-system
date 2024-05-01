package com.xyzbank.metadataservice.model;

import jakarta.persistence.*;
import lombok.*;

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
