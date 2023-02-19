package com.example.bankomatbackend.Entity;

import com.example.bankomatbackend.DataLoader.AbstractClass;
import com.example.bankomatbackend.DataLoader.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Hodimlar extends AbstractClass implements UserDetails {
    public Hodimlar(String ism, String familiya, String tel, String username, String password, String pochta, Lavozim lavozim, boolean enabled) {
        this.ism = ism;
        this.familiya = familiya;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.pochta = pochta;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }

    @Column(nullable = false)
    private String ism;

    @Column(nullable = false)
    private String familiya;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    private String pochta;

    @OneToOne
    private Address address;

    @ManyToOne
    private Lavozim lavozim;

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquqlar> huquqlar = this.lavozim.getHuquqlar();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (Huquqlar huquqlar1 : huquqlar) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(huquqlar1.name()));
        }
        return grantedAuthorityList;
    }
}
