package com.example.bankomatbackend.Service;

import com.example.bankomatbackend.DataLoader.APIResponse;
import com.example.bankomatbackend.Dto.UsersDto;
import com.example.bankomatbackend.Entity.Address;
import com.example.bankomatbackend.Entity.Users;
import com.example.bankomatbackend.Repository.AddreRepository;
import com.example.bankomatbackend.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AddreRepository addreRepository;

    public APIResponse add(UsersDto usersDto) {
        Optional<Address> byViloyatAndTumanAndKuchaAndUy = addreRepository.findByViloyatAndTumanAndKuchaAndUy(usersDto.getViloyat(), usersDto.getTuman(), usersDto.getKucha(), usersDto.getUy());
        if(!byViloyatAndTumanAndKuchaAndUy.isPresent()){
            Users users = new Users();
            users.setIsm(usersDto.getIsm());
            users.setFamiliya(usersDto.getFamiliya());
            users.setOtasiningIsmi(usersDto.getOtasiningIsmi());
            users.setPassportSeriyasi(usersDto.getPassportSeriya());
            users.setPassportRaqami(users.getPassportRaqami());
            users.setTel(usersDto.getTel());
            Address address=new Address();
            address.setViloyat(usersDto.getViloyat());
            address.setTuman(usersDto.getTuman());
            address.setKucha(usersDto.getKucha());
            address.setUy(usersDto.getUy());
            Address addressave = addreRepository.save(address);
            users.setAddress(addressave);
            usersRepository.save(users);
            return new APIResponse("Users added succesfully :)",true);
        }
        return new APIResponse("Address already exist :(",false);
    }

    public APIResponse read(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            return new APIResponse(users.toString(),true);
        }
        return new APIResponse("User not found",false);
    }

    public APIResponse delete(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            usersRepository.deleteById(id);
            return new APIResponse("User deleted succesfully",true);
        }
        return new APIResponse("User not found",false);
    }

    public APIResponse edit(Integer id, UsersDto usersDto) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            users.setIsm(usersDto.getIsm());
            users.setFamiliya(users.getFamiliya());
            users.setOtasiningIsmi(usersDto.getOtasiningIsmi());
            users.setPassportSeriyasi(usersDto.getPassportSeriya());
            users.setPassportRaqami(users.getPassportRaqami());
            users.setTel(usersDto.getTel());
            Optional<Address> byaddres = addreRepository.findById(users.getAddress().getId());
            Address address = byaddres.get();
            address.setViloyat(usersDto.getViloyat());
            address.setTuman(usersDto.getTuman());
            address.setKucha(usersDto.getKucha());
            address.setUy(usersDto.getUy());
            Address save = addreRepository.save(address);
            users.setAddress(save);
            usersRepository.save(users);
            return new APIResponse("User edited succesfully",true);
        }
        return new APIResponse("User not found",false);
    }
}
