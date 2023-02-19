package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Entity.Lavozim;
import com.example.bankomatbackend.Repository.LavozimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;
    public APIResponse add(Lavozim lavozim) {
        Lavozim lavozim1 = new Lavozim();
        lavozim1.setNomi(lavozim.getNomi());
        lavozim1.setHuquqlar(lavozim.getHuquqlar());
        lavozim1.setNomi(lavozim.getNomi());
        lavozimRepository.save(lavozim);
        return new APIResponse("Lavozim added",true);
    }
}
