package com.ioproj.niekopce.Model.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AddUserDTO {

    @NotEmpty(message = "Enter username")
    private String username;

    @NotEmpty(message = "Enter password")
    private String password;

    @NotEmpty(message = "Enter email")
    @Email(message = "Enter valid email")
    private String email;
}
