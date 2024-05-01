package com.xyzbank.eob.repository;

import com.xyzbank.eob.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDocumentRepository extends JpaRepository<Document, Long> {
}
