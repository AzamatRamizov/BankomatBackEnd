package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.BankomatDto;
import com.example.bankomatbackend.Entity.Bankomat;
import com.example.bankomatbackend.Service.BankomatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankomat")
public class BankomatController {

    @Autowired
    BankomatService bankomatService;

    @PostMapping("/pultoldirish/{id}")
    public HttpEntity<?> toldir(@RequestBody BankomatDto bankomatDto, @PathVariable Integer id){
        APIResponse apiResponse=bankomatService.toldir(bankomatDto,id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Bankomat bankomat){
        APIResponse apiResponse=bankomatService.add(bankomat);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @GetMapping("/pulnikur")
    public HttpEntity<?> kurr(){
        APIResponse apiResponse=bankomatService.kur();
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
