package com.siemens.meetingschedulersystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


//// Clasa User reprezintă un utilizator din sistem
@Entity
@Table(name = "Meeting_Scheduler_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
}
)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 25)
    private String username;
    @NotBlank
    @Size(max = 25)
    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

//    public User(String username, String password, String email, Set<ERole> roles) {
//
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.roles = roles.stream().map(role -> new Role(role)).collect(Collectors.toSet());
//
//
//
//    }
    public User(String username, String password, String email, Set<Role> roles) {

        this.username = username;
        this.password = password;
        this.email = email;



    }
}