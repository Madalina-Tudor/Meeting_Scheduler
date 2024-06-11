package com.siemens.meetingschedulersystem.controller;


//pentru a gestiona funcționalitatea întâlnirilor.


import com.siemens.meetingschedulersystem.dto.MeetingDTO;
import com.siemens.meetingschedulersystem.kafka.MeetingNotificationProducer;
import com.siemens.meetingschedulersystem.service.MeetingService;
import com.siemens.meetingschedulersystem.service.notification.EmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingNotificationProducer meetingNotificationProducer;

    @Autowired
    private EmailNotificationService emailNotificationService;
    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);



    @GetMapping("/send-test-email")
    public String sendTestEmail() {
        emailNotificationService.sendSimpleEmail();
        return "E-mail simplu de test trimis!";
    }
    @PostMapping("/send-notification")
    public void sendMeetingNotification(@RequestBody MeetingDTO meeting) {
        logger.info("Received meeting data: {}", meeting);

        meetingNotificationProducer.sendMeetingNotification(meeting);
    }

    @GetMapping("/all")
    public List<MeetingDTO> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("/{id}")
    public MeetingDTO getMeetingById(@PathVariable Long id) {
        return meetingService.getMeetingById(id);
    }

    @PostMapping("/createMeeting")
    public MeetingDTO createMeeting(@RequestBody MeetingDTO meetingDTO) {
        return meetingService.createMeeting(meetingDTO);
    }

    @PutMapping("/{id}")
    public MeetingDTO updateMeeting(@PathVariable Long id, @RequestBody MeetingDTO meetingDTO) {
        return meetingService.updateMeeting(id, meetingDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
    }
}

