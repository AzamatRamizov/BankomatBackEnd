package com.example.bankomatbackend.DataLoader;

import com.example.bankomatbackend.Entity.Address;
import com.example.bankomatbackend.Entity.Card;
import com.example.bankomatbackend.Entity.Hodimlar;
import com.example.bankomatbackend.Entity.Lavozim;
import com.example.bankomatbackend.Repository.AddreRepository;
import com.example.bankomatbackend.Repository.CardRepository;
import com.example.bankomatbackend.Repository.HodimRepository;
import com.example.bankomatbackend.Repository.LavozimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.bankomatbackend.DataLoader.Huquqlar.*;

@Component
public class AutoLoader implements CommandLineRunner {
    @Value(value = "${spring.sql.init.mode}")
    String firstLoad;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    HodimRepository hodimRepository;

    @Override
    public void run(String... args) throws Exception {
        if(firstLoad.equals("always")){
            Huquqlar[] values=Huquqlar.values();
            Lavozim direktor = lavozimRepository.save(new Lavozim(
                    LavozimConst.DIREKTOR,
                    Arrays.asList(values),
                    "Direktor"
            ));
            Lavozim hodim=lavozimRepository.save(new Lavozim(
                    LavozimConst.HODIM,
                    Arrays.asList(ADDKOMISSIYA,DELETEKOMISSIYA,READKOMISSIYA,EDITKOMISSIYA,ADDUSER,EDITUSER,DELETEUSER,READUSER),
                    "Hodim"
            ));
            Lavozim user=lavozimRepository.save(new Lavozim(
                    LavozimConst.USER,
                    Arrays.asList(READMYMONEY,PULTOLDIRISH,PULYECHISH,PAROL),
                    "User"
            ));
//            cardRepository.save(new Card(
//                    "Ramizov Azamat",passwordEncoder.encode("0000"),direktor,"Azamatbek",true,"Azamatbek"
//            ));
//            cardRepository.save(new Card(
//                    "Ramizov Muhammad",passwordEncoder.encode("1111"),hodim,true,"Azamatbek"
//            ));
            Hodimlar hodimlar=hodimRepository.save(new Hodimlar(
                    "Azamat","Ramizov","+998991999991","Azamatbek", passwordEncoder.encode("0000"),"azamatbekromizov@gmail.com",direktor,true
            ));
            Hodimlar hodimlar1=hodimRepository.save(new Hodimlar(
                    "Jamshid","Ramizov","+998922345678","Jamshid4480", passwordEncoder.encode("1111"), "hodim@gmail.com",hodim,true
            ));
            Card card=cardRepository.save(new Card(
                    passwordEncoder.encode("0000"),direktor,"Azamatbek",true
            ));
        }
    }
}
