package com.example.bankomatbackend.Entity;

import com.example.bankomatbackend.DataLoader.AbstractClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Hisobot extends AbstractClass {

    @Column(nullable = false)
    private String nomi;
    
    @Column(nullable = false)
    private String amaliyotTuri;

    @Column(nullable = false)
    private Timestamp amaliyotVaqti;
}
