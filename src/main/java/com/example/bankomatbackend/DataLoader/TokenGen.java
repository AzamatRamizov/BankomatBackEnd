package com.example.bankomatbackend.DataLoader;

import com.example.bankomatbackend.Entity.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGen {
    String parol="0000";
    public String token(String username, Lavozim lavozimlar){

        long vaqt=24*60*60*1000;
        Date muddat=new Date(System.currentTimeMillis()+vaqt);


        String tokenn = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(muddat)
                .claim("roles",lavozimlar.getNomi())
                .signWith(SignatureAlgorithm.HS256, parol)
                .compact();
        return  tokenn;
    }

    public boolean tokenCheck(String token){
        Jwts
                .parser()
                .setSigningKey(parol)
                .parseClaimsJws(token);
        return true;
    }
    public String getUsername(String token){
        String subject = Jwts
                .parser()
                .setSigningKey(parol)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }
}
