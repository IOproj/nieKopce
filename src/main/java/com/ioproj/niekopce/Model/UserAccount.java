package com.ioproj.niekopce.Model;

import com.ioproj.niekopce.Model.DTO.AddUserDTO;
import lombok.*;

import javax.persistence.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dbId;
    private UUID uuid;
    private String username;
    private String password;
    private String email;
    private Boolean isServiceman;

    @OneToOne
    private Certification certification;

    @OneToOne
    private HeatingDevice device;

    @Override
    public int hashCode() {
        return  7*13*dbId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        return ((obj instanceof UserAccount) && ((UserAccount) obj).dbId == this.dbId);
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
        this.isServiceman=false;
    }
}
