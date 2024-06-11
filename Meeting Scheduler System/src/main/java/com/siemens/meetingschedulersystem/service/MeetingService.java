package com.siemens.meetingschedulersystem.service;

import com.siemens.meetingschedulersystem.dto.MeetingDTO;
import com.siemens.meetingschedulersystem.kafka.MeetingNotificationProducer;
import com.siemens.meetingschedulersystem.model.Meeting;
import com.siemens.meetingschedulersystem.model.User;
import com.siemens.meetingschedulersystem.repository.MeetingRepository;
import com.siemens.meetingschedulersystem.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {
    @Autowired
    private MeetingNotificationProducer meetingNotificationProducer;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private UserRepository userRepository;

    public List<MeetingDTO> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingDTO> meetingDTOs = new ArrayList<>();
        for (Meeting meeting : meetings) {
            meetingDTOs.add(convertToDTO(meeting));
        }
        return meetingDTOs;
    }

    public MeetingDTO getMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Meeting not found with id " + id));
        return convertToDTO(meeting);
    }

    public MeetingDTO createMeeting(MeetingDTO meetingDTO) {

        String organizerEmail = meetingDTO.getOrganizerEmail();
        if (StringUtils.isEmpty(organizerEmail)) {
            throw new IllegalArgumentException("Organizer email is required");
        }

        User organizer = userRepository.findByEmail(organizerEmail);
        if (organizer == null) {
            throw new EntityNotFoundException("User not found with email " + organizerEmail);
        }

        Meeting meeting = convertToEntity(meetingDTO);
        meeting.setOrganizer(organizer);
        meeting = meetingRepository.save(meeting);

        return convertToDTO(meeting);
       /* Meeting meeting = convertToEntity(meetingDTO);

        User organizer = userRepository.findByEmail(meetingDTO.getOrganizerEmail());
        if (organizer == null) {
            throw new EntityNotFoundException("User not found with email " + meetingDTO.getOrganizerEmail());
        }

        meeting.setOrganizer(organizer);

        meeting = meetingRepository.save(meeting);
        return convertToDTO(meeting);*/
    }

    public MeetingDTO updateMeeting(Long id, MeetingDTO meetingDTO) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Meeting not found with id " + id));
        meeting.setTitle(meetingDTO.getTitle());
        meeting.setDescription(meetingDTO.getDescription());
        meeting.setStartTime(meetingDTO.getStartTime());
        meeting.setEndTime(meetingDTO.getEndTime());
      //  meeting.setOrganizer(userRepository.findById(meetingDTO.getOrganizerId()).orElseThrow(() -> new EntityNotFoundException("User not found with id " + meetingDTO.getOrganizerId())));
        meeting.setLocation(meetingDTO.getLocation());
        meeting = meetingRepository.save(meeting);
        return convertToDTO(meeting);
    }

    public void deleteMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Meeting not found with id " + id));
        meetingRepository.delete(meeting);
    }

    private MeetingDTO convertToDTO(Meeting meeting) {
        MeetingDTO meetingDTO = new MeetingDTO();
        meetingDTO.setId(meeting.getId());
        meetingDTO.setTitle(meeting.getTitle());
        meetingDTO.setDescription(meeting.getDescription());
        meetingDTO.setStartTime(meeting.getStartTime());
        meetingDTO.setEndTime(meeting.getEndTime());
        meetingDTO.setOrganizerId(meeting.getOrganizer().getId());
        meetingDTO.setOrganizerEmail(meeting.getOrganizer().getEmail());
        meetingDTO.setLocation(meeting.getLocation());
        return meetingDTO;
    }

    public Meeting convertToEntity(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        meeting.setId(meetingDTO.getId());
        meeting.setTitle(meetingDTO.getTitle());
        meeting.setDescription(meetingDTO.getDescription());
        meeting.setStartTime(meetingDTO.getStartTime());
        meeting.setEndTime(meetingDTO.getEndTime());
        meeting.setLocation(meetingDTO.getLocation());
        meetingNotificationProducer.sendMeetingNotification(meetingDTO);

        return meeting;
    }


}//MeetingService
