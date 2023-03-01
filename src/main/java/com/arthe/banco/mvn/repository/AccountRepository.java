package com.arthe.banco.mvn.repository;

import com.arthe.banco.mvn.repository.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
