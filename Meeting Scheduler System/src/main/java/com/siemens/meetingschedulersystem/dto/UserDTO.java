package com.siemens.meetingschedulersystem.dto;

import com.siemens.meetingschedulersystem.model.ERole;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
   // pentru a defini clasele DTO (Data Transfer Objects) utilizate pentru transferul datelor între backend și frontend
   @NotBlank
   @Size(min = 3, max = 10)
   private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    private String password;

    @NotBlank
    @Size(max = 15)
    @Email
    private String email;
    private Set<ERole> roles;// adăugăm un câmp pentru roluri


    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(String username, String email, String password, Set<ERole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles; // initializăm lista de roluri
    }
}
