package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.KomissiyaDto;
import com.example.bankomatbackend.Entity.Bank;
import com.example.bankomatbackend.Entity.Komissiya;
import com.example.bankomatbackend.Repository.BankRepository;
import com.example.bankomatbackend.Repository.KomissiyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KomissiyaService {
    @Autowired
    KomissiyaRepository komissiyaRepository;
    @Autowired
    BankRepository bankRepository;
    public APIResponse add(KomissiyaDto komissiyaDto) {
        Optional<Bank> byNomi = bankRepository.findByNomi(komissiyaDto.getBankNomi());
        if(byNomi.isPresent()){
            Komissiya komissiya=new Komissiya();
            komissiya.setKomissiyayechish(komissiyaDto.getKomissiyayechish());
            komissiya.setKomissiyatoldirish(komissiyaDto.getKomissiyatoldirish());
            komissiya.setBank(byNomi.get());
            komissiyaRepository.save(komissiya);
            return new APIResponse("Komissiya qo'shildi",true);
        }
        return new APIResponse("Bank doesn't exist",false);
    }

    public APIResponse edit(KomissiyaDto komissiyaDto, String nomi) {
        Optional<Bank> byNomi = bankRepository.findByNomi(nomi);
        Bank bank = byNomi.get();
        Optional<Komissiya> byBank = komissiyaRepository.findByBank(bank);
        if (byBank.isPresent()){
            Komissiya komissiya = byBank.get();
            komissiya.setKomissiyayechish(komissiyaDto.getKomissiyayechish());
            komissiya.setKomissiyatoldirish(komissiyaDto.getKomissiyatoldirish());
            komissiya.setBank(bank);
            komissiyaRepository.save(komissiya);
            return new APIResponse("Komissiya edited succesfully",true);
        }
        return new APIResponse("Bank doesn't exist",false);
    }

    public APIResponse del(String nomi) {
        Optional<Bank> byNomi = bankRepository.findByNomi(nomi);
        Bank bank = byNomi.get();
        Optional<Komissiya> byBank = komissiyaRepository.findByBank(bank);
        if(byBank.isPresent()){
            Komissiya komissiya = byBank.get();
            komissiyaRepository.deleteById(komissiya.getId());
            return new APIResponse("Komissiya deleted",true);
        }
        return new APIResponse("Bank doesn't exist",false);
    }

    public APIResponse read(String nomi) {
        Optional<Bank> byNomi = bankRepository.findByNomi(nomi);
        Bank bank = byNomi.get();
        Optional<Komissiya> byBank = komissiyaRepository.findByBank(bank);
        if(byBank.isPresent()){
            Komissiya komissiya = byBank.get();
            return new APIResponse(komissiya.toString(),true);
        }
        return new APIResponse("Bank doesn't exist",false);
    }
}
