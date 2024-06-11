package com.siemens.meetingschedulersystem.security.services;

public class UserDetailsServiceImpl {
}
//package com.siemens.meetingschedulersystem.service;
//
//import com.siemens.meetingschedulersystem.dto.UserDTO;
//import com.siemens.meetingschedulersystem.entity.User;
//import com.siemens.meetingschedulersystem.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDTO authenticateUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new UserDTO(user.getUsername(), user.getEmail());
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), new ArrayList<>());
//    }
//}
