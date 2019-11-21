package com.ioproj.niekopce.Model;

import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
        return  7*13*dbId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //TODO: Implementation
        return super.equals(obj);
    }

    public AddUserDTO dto(){
        AddUserDTO addUserDTO = new AddUserDTO();
        addUserDTO.setUsername(this.username);
        addUserDTO.setPassword(this.password);
        addUserDTO.setEmail(this.email);
        return addUserDTO;
    }

    public UserAccount(AddUserDTO dto){
        this.username=dto.getUsername();
        this.email=dto.getEmail();
    }
}
