package com.xyzbank.authenticationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "document_type_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "documentType")
    private List<Document> documents;
}
