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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Card extends AbstractClass implements UserDetails {

    @ManyToOne
    private Users users;


    private String password;


    private String cardNumber;

    @ManyToOne
    private Bank bank;


    private String amalMuddat;

    @ManyToOne
    private CardName cardName;

    @ManyToOne
    private Lavozim lavozim;


    private long money;


    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;

    public Card(String password, Lavozim lavozim,String cardNumber ,boolean enabled) {
        this.password = password;
        this.lavozim = lavozim;
        this.cardNumber=cardNumber;
        this.enabled = enabled;
    }

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

    @Override
    public String getUsername() {
        return cardNumber;
    }

}
