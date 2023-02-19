package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.LoginDto;
import com.example.bankomatbackend.Service.HodimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hodim")
public class HodimController {
    @Autowired
    HodimService hodimService;
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        APIResponse apiResponse=hodimService.login(loginDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
