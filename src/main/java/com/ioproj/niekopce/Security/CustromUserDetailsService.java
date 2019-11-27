package com.ioproj.niekopce.Security;

import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustromUserDetailsService implements UserDetailsService {
    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userService.get(username);
        String role;
        if(userAccount.getIsServiceman()){
            role="ADMIN";
        }
        else {
            role = "USER";
        }

        return CustomUserDetails.builder()
                .id(userAccount.getDbId())
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role))) //TODO role from DB
                .build();
    }
}
