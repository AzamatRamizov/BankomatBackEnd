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
public class Users extends AbstractClass {

    @Column(nullable = false)
    private String ism;

    @Column(nullable = false)
    private String familiya;

    @Column(nullable = false)
    private String otasiningIsmi;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String passportSeriyasi;

    @Column(nullable = false)
    private long passportRaqami;

    @OneToOne
    private Address address;
}
