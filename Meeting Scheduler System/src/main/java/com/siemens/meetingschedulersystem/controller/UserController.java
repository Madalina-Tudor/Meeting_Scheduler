package com.siemens.meetingschedulersystem.controller;


import com.siemens.meetingschedulersystem.dto.UserDTO;
import com.siemens.meetingschedulersystem.model.ERole;
import com.siemens.meetingschedulersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR','    ROLE_ADMIN')")

    @PostMapping("/signup")

    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto) {

        Set<ERole> roles = new HashSet<>();
        roles.add(ERole.ROLE_USER);
        userDto.setRoles(roles);
        try {
            UserDTO registeredUserDTO = userService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUserDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDTO("ERRROOORUsernameNotCreated", "Error: " + e.getMessage()));
        }
    }



 @PostMapping("/login")
 public ResponseEntity<UserDTO> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
     try {
         UserDTO userDTO = userService.loginUser(email, password);
         return ResponseEntity.ok(userDTO);
     } catch (UsernameNotFoundException | BadCredentialsException e) {
         e.printStackTrace();

         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     }
 }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam String email, @RequestParam String password) {
        try {
            userService.deleteUser(email, password);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email/password");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user: " + e.getMessage());
        }
    }}