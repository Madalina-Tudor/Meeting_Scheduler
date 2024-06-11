package com.siemens.meetingschedulersystem.kafka;
// Acest pachet este destinat producerilor și consumatorilor Kafka,
// care sunt responsabili pentru trimiterea și primirea mesajelor în cadrul sistemului de coadă de mesaje Kafka.
//consumatorul MeetingNotificationConsumer ascultă și procesează aceste mesaje pentru a trimite notificări către utilizatori.


import com.siemens.meetingschedulersystem.dto.MeetingDTO;
import com.siemens.meetingschedulersystem.service.notification.EmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MeetingNotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingNotificationConsumer.class);
    @Autowired
    private EmailNotificationService emailNotificationService;




    //@KafkaListener(topicPartitions = { @TopicPartition(topic = "meeting_notifications",
    //                                                    partitions = {"0"}) },
    //                                                    groupId = "group_id")

    @KafkaListener(topics = "meeting_notification-topic", groupId = "group_id")
    public void consumeMeetingNotification(MeetingDTO meetingDTO) {
        LOGGER.info("Received meeting notification from partition 0: {}", meetingDTO);
        emailNotificationService.sendMeetingNotification(meetingDTO);
        emailNotificationService.sendSimpleEmail();

    }
}


