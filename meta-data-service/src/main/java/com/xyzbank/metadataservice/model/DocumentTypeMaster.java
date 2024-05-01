package com.xyzbank.metadataservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "document_type_master")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentTypeMaster extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public DocumentTypeMaster(String name) {
        this.name = name;
    }
}
