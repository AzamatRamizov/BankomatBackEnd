package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddreRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByViloyatAndTumanAndKuchaAndUy(String viloyat, String tuman, String kucha, String uy);
}
