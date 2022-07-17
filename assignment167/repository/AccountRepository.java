package com.example.assignment167.repository;

import com.example.assignment167.entiy.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByUsername(String username);
}
