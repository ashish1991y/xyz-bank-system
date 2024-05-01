package com.xyzbank.metadataservice.dto.response;

import com.xyzbank.metadataservice.model.StatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusMasterRepository extends JpaRepository<StatusMaster, Long> {
}
