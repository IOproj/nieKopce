package com.ioproj.niekopce.Services;


import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Repositories.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private  final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public void  addUser(AddUserDTO dto){
        UserAccount userAccount = new UserAccount(dto);
        userAccount.setUuid(UUID.randomUUID());
        userAccount.setPassword(passwordEncoder.encode(dto.getPassword()));
       userAccountRepository.save(userAccount);
    }

    public UserAccount get(String username){
        return userAccountRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("No user found"));
    }

    public boolean isAdmin(String name) {
        UserAccount userAccount = userAccountRepository.findByUsername(name).orElseThrow(()->
                new RuntimeException("No user found"));
        return userAccount.getIsServiceman();
    }
}
