package com.siemens.meetingschedulersystem.kafka;
// Acest pachet este destinat producerilor și consumatorilor Kafka,
// care sunt responsabili pentru trimiterea și primirea mesajelor în cadrul sistemului de coadă de mesaje Kafka.
// Producerul MeetingNotificationProducer trimite notificările despre întâlniri către coada de mesaje Kafka,


import com.siemens.meetingschedulersystem.dto.MeetingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MeetingNotificationProducer {

    private static final String MEETING_TOPIC = "meeting_notification-topic";
    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingNotificationProducer.class);

    @Autowired
    private KafkaTemplate<String, MeetingDTO> kafkaTemplate;

    public void sendMeetingNotification(MeetingDTO meetingDTO) {
        LOGGER.info("Sending meeting notification: {}", meetingDTO);
        kafkaTemplate.send(MEETING_TOPIC, meetingDTO);
    }

}

