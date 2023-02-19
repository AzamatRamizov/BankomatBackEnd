package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.DataLoader.TokenGen;
import com.example.bankomatbackend.Dto.CardDto;
import com.example.bankomatbackend.Dto.LoginDto;
import com.example.bankomatbackend.Dto.ParolDto;
import com.example.bankomatbackend.Dto.PulDto;
import com.example.bankomatbackend.Entity.*;
import com.example.bankomatbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class CardService implements UserDetailsService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TokenGen tokenGen;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CardNameRepository cardNameRepository;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    HodimRepository hodimRepository;

    @Autowired
    KomissiyaRepository komissiyaRepository;

    public APIResponse add(CardDto cardDto) {
        Optional<Bank> byBankNomi = bankRepository.findByNomi(cardDto.getBankName());
        if(!byBankNomi.isPresent()){
            return new APIResponse("Bank mavjud emas",false);
        }
        boolean b = cardRepository.existsByCardNumber(cardDto.getCardNumber());
        Optional<CardName> byNomi = cardNameRepository.findByNomi(cardDto.getCardName());
        if(!byNomi.isPresent()){
            return new APIResponse("CardName doesn't exist",false);
        }
        Optional<Lavozim> byNomi1 = lavozimRepository.findByNomi(cardDto.getLavozim());
        if(!byNomi1.isPresent()){
            return new APIResponse("Lavozim doesn't exist",false);
        }
        Optional<Users> byIsmAndFamiliya = usersRepository.findByIsmAndFamiliya(cardDto.getIsm(), cardDto.getFamiliya());
        if(!byIsmAndFamiliya.isPresent()){
            return new APIResponse("User not found ",false);
        }
        if(!b){
            Card card=new Card();
            Users users = byIsmAndFamiliya.get();
            card.setUsers(users);
            card.setPassword(passwordEncoder.encode(cardDto.getPassword()));
            card.setCardNumber(cardDto.getCardNumber());
            card.setBank(byBankNomi.get());
            card.setCardName(byNomi.get());
            card.setMoney(cardDto.getMoney());
            card.setLavozim(byNomi1.get());
            card.setEnabled(true);
            cardRepository.save(card);
            return new APIResponse("Card uploading",true);
        }
        return new APIResponse("This card already exist",false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Hodimlar> byUsername = hodimRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return (UserDetails) byUsername.get();
        }
        Optional<Card> byCardNumber = cardRepository.findByCardNumber(username);
        if(byCardNumber.isPresent()){
            return (UserDetails) byCardNumber.get();
        }
        return (UserDetails) new UsernameNotFoundException("Topilmadi");
    }

    public APIResponse login(LoginDto loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            Card user = (Card) authenticate.getPrincipal();
            String token = tokenGen.token(user.getCardNumber(), user.getLavozim());
            return new APIResponse("Platformaga kirildi: "+token,true);
        }
        catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }
    }

    public APIResponse readmymoney() {
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Card user = (Card) authentication.getPrincipal();
            Optional<Card> byId = cardRepository.findById(user.getId());
            return new APIResponse("Hisobingizda: "+user.getMoney()+" so'm mavjud.",true);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Nimadir hato! Qaytadan urinib ko'ring.",false);
        }
    }

    @Autowired
    BankomatRepository bankomatRepository;

    public APIResponse toldir(PulDto pulDto) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Card user = (Card) authentication.getPrincipal();
            Optional<Komissiya> byBank = komissiyaRepository.findByBank(user.getBank());
            Komissiya komissiya = byBank.get();
            long summa= user.getMoney();
            summa+=(pulDto.getSumma()*komissiya.getKomissiyatoldirish());
            Optional<Card> byId = cardRepository.findById(user.getId());
            Card card = byId.get();
            card.setMoney(summa);
            cardRepository.save(card);
            Optional<Bankomat> banko = bankomatRepository.findById(1);
            Bankomat bankomat = banko.get();
            long bankopul=bankomat.getUmumiypul()+ pulDto.getSumma();
            bankomat.setUmumiypul(bankopul);
            bankomatRepository.save(bankomat);
            return new APIResponse("Pul o'tkazildi",true);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }

    }

    public APIResponse yech(PulDto pulDto) {
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Card user = (Card) authentication.getPrincipal();
            Optional<Komissiya> byBank = komissiyaRepository.findByBank(user.getBank());
            Komissiya komissiya = byBank.get();
            long usersumma=user.getMoney();
            if((pulDto.getSumma()*komissiya.getKomissiyayechish())>usersumma){
                return new APIResponse("Mablag' yetarli emas",false);
            }
            Optional<Card> byId = cardRepository.findById(user.getId());
            Card card = byId.get();
            usersumma-= pulDto.getSumma()*komissiya.getKomissiyayechish();
            card.setMoney(usersumma);
            cardRepository.save(card);
            Optional<Bankomat> banko = bankomatRepository.findById(1);
            Bankomat bankomat = banko.get();
            long bankopul=bankomat.getUmumiypul()- pulDto.getSumma();
            bankomat.setUmumiypul(bankopul);
            bankomatRepository.save(bankomat);
            if(bankomat.getUmumiypul()<15000000){
                xabaryuborishuserga("azamatbekromizov@gmail.com","Bankomatda 1500000 dan kam pul qoldi");
            }
            return new APIResponse("Pul yeсhildi",true);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }
    }
    @Autowired
    JavaMailSender javaMailSender;
    public boolean xabaryuborishuserga(String email,String matn){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(email);
            helper.setSubject("Email tasdiqlash");
            helper.setText(matn,true);
            javaMailSender.send(message);
            return  true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }

    public APIResponse parol(ParolDto parolDto) {
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Card user = (Card) authentication.getPrincipal();
            if(!parolDto.getNewparol().equals(parolDto.getReparol())){
                return new APIResponse("parollar mos emas",false);
            }
            Optional<Card> byId = cardRepository.findById(user.getId());
            if(byId.isPresent()){
                Card card = byId.get();
                card.setPassword(passwordEncoder.encode(parolDto.getNewparol()));
                cardRepository.save(card);
                return new APIResponse("Parol "+parolDto.getNewparol()+" ga o'zgardi.",true);
            }
            return new APIResponse("Xato",false);
        }catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login yoki parol xato",false);
        }
    }
}
