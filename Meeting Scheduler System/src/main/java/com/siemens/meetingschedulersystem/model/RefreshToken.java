//package com.siemens.meetingschedulersystem.model;
//
//import com.siemens.meetingschedulersystem.entity.User;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity(name = "refreshtoken")
//
//public class RefreshToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @Column(nullable = false, unique = true)
//    private String token;
//
//}
