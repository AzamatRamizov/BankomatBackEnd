package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.BankDto;
import com.example.bankomatbackend.Entity.Address;
import com.example.bankomatbackend.Entity.Bank;
import com.example.bankomatbackend.Repository.AddreRepository;
import com.example.bankomatbackend.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AddreRepository addreRepository;

    public APIResponse add(BankDto bankDto) {
        Optional<Address> byViloyatAndTumanAndKuchaAndUy = addreRepository.findByViloyatAndTumanAndKuchaAndUy(bankDto.getViloyat(), bankDto.getTuman(), bankDto.getKucha(), bankDto.getUy());
        if(byViloyatAndTumanAndKuchaAndUy.isPresent()){
            return new APIResponse("Addres is exist",false);
        }
        Optional<Bank> byNomi = bankRepository.findByNomi(bankDto.getNomi());
        if(!byNomi.isPresent()){
            Bank bank=new Bank();
            bank.setNomi(bankDto.getNomi());
            Address address=new Address();
            address.setViloyat(bankDto.getViloyat());
            address.setTuman(bankDto.getTuman());
            address.setKucha(bankDto.getKucha());
            address.setUy(bankDto.getUy());
            Address save = addreRepository.save(address);
            bank.setAddress(save);
            bankRepository.save(bank);
            return new APIResponse("Bank added succesfully",true);
        }
        return new APIResponse("Bank already exist",false);
    }
}
