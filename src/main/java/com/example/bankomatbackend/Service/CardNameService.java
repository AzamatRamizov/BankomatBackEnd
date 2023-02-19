package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Entity.CardName;
import com.example.bankomatbackend.Repository.CardNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardNameService {
    @Autowired
    CardNameRepository cardNameRepository;
    public APIResponse add(CardName cardName) {
        Optional<CardName> byNomi = cardNameRepository.findByNomi(cardName.getNomi());
        if(byNomi.isPresent()){
            return new APIResponse("Mavjud",false);
        }
        cardNameRepository.save(cardName);
        return new APIResponse("Added succesfully",true);
    }

}
