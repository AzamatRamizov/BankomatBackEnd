package com.example.bankomatbackend.Controller;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.UsersDto;
import com.example.bankomatbackend.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @PreAuthorize(value = "hasAuthority('ADDUSER')")
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody UsersDto usersDto){
        APIResponse apiResponse=usersService.add(usersDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READUSER')")
    @GetMapping("/read/{id}")
    public HttpEntity<?> read(@PathVariable Integer id){
        APIResponse apiResponse=usersService.read(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETEUSER')")
    @DeleteMapping("/delete{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        APIResponse apiResponse=usersService.delete(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITUSER')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody UsersDto usersDto,@PathVariable Integer id){
        APIResponse apiResponse=usersService.edit(id,usersDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
