package com.xyzbank.eob.repository;

import com.xyzbank.eob.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIbanNumber(String ibanNumber);
}
