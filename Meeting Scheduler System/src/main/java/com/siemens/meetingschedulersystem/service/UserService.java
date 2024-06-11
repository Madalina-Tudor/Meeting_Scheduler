package com.siemens.meetingschedulersystem.service;

import com.siemens.meetingschedulersystem.dto.UserDTO;
import com.siemens.meetingschedulersystem.exception.UserAlreadyExistsException;
import com.siemens.meetingschedulersystem.model.ERole;
import com.siemens.meetingschedulersystem.model.Role;
import com.siemens.meetingschedulersystem.model.User;
import com.siemens.meetingschedulersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

   public UserDTO registerUser(UserDTO userDto) {
       try {
           if (userRepository.findByEmail(userDto.getEmail()) != null) {
               throw new UserAlreadyExistsException("A user with this email already exists.");
           }
           if (userRepository.findByUsername(userDto.getUsername()) != null) {
               throw new UserAlreadyExistsException("A user with this username already exists.");
           }

           User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());


           for (ERole eRole : userDto.getRoles()) {
               Role role = new Role(eRole);
               user.getRoles().add(role);
           }


          User savedUser =userRepository.save(user);
           return new UserDTO(savedUser.getUsername(), savedUser.getEmail());
       } catch (UserAlreadyExistsException e) {
           throw e;
       } catch (Exception e) {
           throw new RuntimeException("Failed to register user.", e);
       }
   }


    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email/password");
        }

        // authentication successful, return user details
        return new UserDTO(user.getUsername(), user.getEmail());
    }


    public void deleteUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email/password");
        }
        userRepository.delete(user);
    }


    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDTOS.add(new UserDTO(user.getUsername(), user.getEmail()));
        }
        return userDTOS;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }



}//UserService
