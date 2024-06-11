package com.siemens.meetingschedulersystem.repository;

import com.siemens.meetingschedulersystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfața UserRepository va permite interacțiunea cu baza de date
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    User findByEmail(String email);


}