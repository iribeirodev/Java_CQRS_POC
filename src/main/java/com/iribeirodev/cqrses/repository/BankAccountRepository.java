package com.iribeirodev.cqrses.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iribeirodev.cqrses.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

}
