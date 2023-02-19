package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.DataLoader.TokenGen;
import com.example.bankomatbackend.Dto.LoginDto;
import com.example.bankomatbackend.Entity.Hodimlar;
import com.example.bankomatbackend.Entity.Users;
import com.example.bankomatbackend.Repository.HodimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HodimService implements UserDetailsService {
    @Autowired
    HodimRepository hodimRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenGen tokenGen;
    public APIResponse login(LoginDto loginDto) {
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            Hodimlar user = (Hodimlar) authenticate.getPrincipal();
            String token = tokenGen.token(user.getUsername(), user.getLavozim());
            return new APIResponse("Platformaga kirildi: "+token,true);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Hodimlar> byUsername = hodimRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();
        }
        return (UserDetails) new UsernameNotFoundException("Topilmadi");
    }
}
