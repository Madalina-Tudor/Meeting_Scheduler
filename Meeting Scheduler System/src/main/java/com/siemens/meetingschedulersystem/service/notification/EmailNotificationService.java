package com.siemens.meetingschedulersystem.service.notification;

//Acest pachet se ocupă de implementarea propriu-zisă a serviciilor de notificare, cum ar fi trimiterea de e-mailuri și SMS-uri.
// iar clasele EmailNotificationService și SmsNotificationService efectuează efectiv trimiterea notificărilor.

import com.siemens.meetingschedulersystem.dto.MeetingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    private static final Logger logger =
            LoggerFactory.getLogger(EmailNotificationService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("madalina.puia21@gmail.com");
        message.setSubject("Test Email");
        message.setText("Acesta este un e-mail simplu de test.");

        mailSender.send(message);
    }


   public void sendMeetingNotification(MeetingDTO meeting) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(meeting.getOrganizerEmail());
        message.setFrom("madalina.puia21@gmail.com");
        message.setSubject("Meeting Reminder: " + meeting.getTitle());
        message.setText("This is a reminder for your upcoming meeting: \n\n" +
                "Title: " + meeting.getTitle() + "\n" +
                "Description: " + meeting.getDescription() + "\n" +
                "Location: " + meeting.getLocation() + "\n" +
                "Start Time: " + meeting.getStartTime() + "\n" +
                "End Time: " + meeting.getEndTime());
        try {
            mailSender.send(message);
            logger.info("Meeting reminder sent to {}", meeting.getOrganizerEmail());
        } catch (MailException e) {
            logger.error("Error sending meeting reminder to {}: {}", meeting.getOrganizerEmail(), e.getMessage());
        }
    }
}