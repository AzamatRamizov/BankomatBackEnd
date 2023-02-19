package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.CardName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardNameRepository extends JpaRepository<CardName,Integer> {
    Optional<CardName> findByNomi(String nomi);
}
