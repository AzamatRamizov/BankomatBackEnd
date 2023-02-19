package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Entity.Lavozim;
import com.example.bankomatbackend.Repository.LavozimRepository;
import com.example.bankomatbackend.Service.LavozimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lavozim")
public class LavozimController {

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    LavozimService lavozimService;
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Lavozim lavozim){
        APIResponse apiResponse=lavozimService.add(lavozim);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
