package com.example.bankomatbackend.Entity;

import com.example.bankomatbackend.DataLoader.AbstractClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Komissiya extends AbstractClass {
    @Column(nullable = false)
    private double komissiyayechish;

    @Column(nullable = false)
    private double komissiyatoldirish;

    @OneToOne
    private Bank bank;
}
