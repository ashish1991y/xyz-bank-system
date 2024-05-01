package com.xyzbank.eob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document extends ParentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentPath;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private DocumentType documentType;

    @ManyToMany(mappedBy = "documents")
    private Set<Account> accounts;

}
