package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Bankomat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankomatRepository extends JpaRepository<Bankomat,Integer> {

}
