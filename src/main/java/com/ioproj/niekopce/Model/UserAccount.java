package com.ioproj.niekopce.Model;

import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue
    private Long dbId;
    private UUID uuid;
    private String username;
    private String password;
    private String email;

    @Override
    public int hashCode() {
        //TODO: Implementation
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //TODO: Implementation
        return super.equals(obj);
    }

    @Override
    public String toString() {
        //TODO: Implementation
        return super.toString();
    }

    public AddUserDTO dto(){
        //TODO Implementation;
        return null;
    }
}
