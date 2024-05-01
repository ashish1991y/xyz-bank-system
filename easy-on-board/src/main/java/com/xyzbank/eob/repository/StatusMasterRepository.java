package com.xyzbank.eob.repository;

import com.xyzbank.eob.model.StatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusMasterRepository extends JpaRepository<StatusMaster, Long> {
}
