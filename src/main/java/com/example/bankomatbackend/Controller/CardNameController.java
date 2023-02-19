package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Entity.CardName;
import com.example.bankomatbackend.Service.CardNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardname")
public class CardNameController {
    @Autowired
    CardNameService cardNameService;
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CardName cardName){
        APIResponse apiResponse=cardNameService.add(cardName);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
