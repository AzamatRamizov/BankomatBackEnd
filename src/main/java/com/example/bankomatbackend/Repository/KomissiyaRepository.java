package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Bank;
import com.example.bankomatbackend.Entity.Komissiya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KomissiyaRepository extends JpaRepository<Komissiya, Integer> {
    Optional<Komissiya> findByBank(Bank bank);
}
