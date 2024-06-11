package com.siemens.meetingschedulersystem.repository;

import com.siemens.meetingschedulersystem.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//M pentru  metodele CRUD pentru întâlniri.
public interface MeetingRepository extends JpaRepository<Meeting,Long> {

    List<Meeting> findByOrganizerId(Long organizerId);

    @Override
    List<Meeting> findAll();
    @Override
    Optional<Meeting> findById(Long aLong);




    Meeting save(Meeting meeting);
}
