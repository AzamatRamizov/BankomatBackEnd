package com.example.bankomatbackend.Repository;

import com.example.bankomatbackend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByIsmAndFamiliya(String ism, String familiya);
}
