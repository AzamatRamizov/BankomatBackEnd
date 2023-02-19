package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.BankomatDto;
import com.example.bankomatbackend.Entity.Bankomat;
import com.example.bankomatbackend.Entity.Card;
import com.example.bankomatbackend.Entity.Hodimlar;
import com.example.bankomatbackend.Repository.BankomatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankomatService {
    @Autowired
    BankomatRepository bankomatRepository;

    public APIResponse toldir(BankomatDto bankomatDto,Integer id) {
        Optional<Bankomat> byId = bankomatRepository.findById(id);
        if(byId.isPresent()){
            Bankomat bankomat = byId.get();
            long summa=bankomat.getUmumiypul();
            summa+=bankomatDto.getSumma();
            bankomat.setUmumiypul(summa);
            bankomatRepository.save(bankomat);
            return new APIResponse("Bankomat to'ldirildi",true);
        }
        return new APIResponse("False",false);
    }

    public APIResponse add(Bankomat bankomat) {
        bankomatRepository.save(bankomat);
        return new APIResponse("OK",true);
    }

    public APIResponse kur() {
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Hodimlar user = (Hodimlar) authentication.getPrincipal();
            if(user.getId()==1){
                Optional<Bankomat> byId = bankomatRepository.findById(1);
                Bankomat bankomat = byId.get();
                return new APIResponse("Pul: "+bankomat.getUmumiypul()+" so'm",true);
            }
            return new APIResponse("False",false);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }
    }
}
