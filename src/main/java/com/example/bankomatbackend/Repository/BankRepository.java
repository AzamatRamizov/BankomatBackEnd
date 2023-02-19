package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank,Integer> {
    Optional<Bank> findByNomi(String nomi);
}
