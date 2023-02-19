package com.example.bankomatbackend.Entity;

import com.example.bankomatbackend.DataLoader.AbstractClass;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends AbstractClass {
    @Column(nullable = false)
    private String viloyat;

    @Column(nullable = false)
    private String  tuman;

    @Column(nullable = false)
    private String kucha;

    @Column(nullable = false)
    private String uy;
}
