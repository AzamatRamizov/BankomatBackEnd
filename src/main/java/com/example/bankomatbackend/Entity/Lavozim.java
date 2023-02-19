package com.example.bankomatbackend.Entity;

import com.example.bankomatbackend.DataLoader.AbstractClass;
import com.example.bankomatbackend.DataLoader.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Lavozim extends AbstractClass {
    @Column(nullable = false)
    private String nomi;

    @ElementCollection
    private List<Huquqlar> huquqlar;

    @Column(nullable = false)
    private String izoh;
}
