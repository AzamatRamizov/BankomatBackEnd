package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.DataLoader.TokenGen;
import com.example.bankomatbackend.Dto.CardDto;
import com.example.bankomatbackend.Dto.LoginDto;
import com.example.bankomatbackend.Dto.ParolDto;
import com.example.bankomatbackend.Dto.PulDto;
import com.example.bankomatbackend.Entity.Card;
import com.example.bankomatbackend.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card1")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CardDto cardDto){
        APIResponse apiResponse=cardService.add(cardDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        APIResponse apiResponse=cardService.login(loginDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @GetMapping("/readmymoney")
    public HttpEntity<?> readmymoney(){
        APIResponse apiResponse=cardService.readmymoney();
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }

    @PostMapping("/hisobtoldir")
    public HttpEntity<?> toldir(@RequestBody PulDto pulDto){
        APIResponse apiResponse=cardService.toldir(pulDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PostMapping("/pulyech")
    public HttpEntity<?> yech(@RequestBody PulDto pulDto){
        APIResponse apiResponse=cardService.yech(pulDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PutMapping("/parol")
    public HttpEntity<?> parol(@RequestBody ParolDto parolDto){
        APIResponse apiResponse=cardService.parol(parolDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
