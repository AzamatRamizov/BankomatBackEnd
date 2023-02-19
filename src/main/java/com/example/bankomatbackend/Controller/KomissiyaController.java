package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.KomissiyaDto;
import com.example.bankomatbackend.Service.KomissiyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/komissiya")
public class KomissiyaController {
    @Autowired
    KomissiyaService komissiyaService;
    @PreAuthorize(value = "hasAuthority('ADDKOMISSIYA')")
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody KomissiyaDto komissiyaDto){
        APIResponse apiResponse=komissiyaService.add(komissiyaDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITKOMISSIYA')")
    @PutMapping("/edit/{nomi}")
    public HttpEntity<?> edit(@RequestBody KomissiyaDto komissiyaDto,@PathVariable String nomi){
        APIResponse apiResponse=komissiyaService.edit(komissiyaDto,nomi);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETEKOMISSIYA')")
    @DeleteMapping("/delete/{nomi}")
    public HttpEntity<?> del(@PathVariable String nomi){
        APIResponse apiResponse=komissiyaService.del(nomi);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READKOMISSIYA')")
    @GetMapping("/read/{nomi}")
    public HttpEntity<?> read(@PathVariable String nomi){
        APIResponse apiResponse=komissiyaService.read(nomi);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
