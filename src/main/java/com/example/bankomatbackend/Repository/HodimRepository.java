package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Hodimlar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HodimRepository extends JpaRepository<Hodimlar,Integer> {
    Optional<Hodimlar> findByUsername(String username);
}
