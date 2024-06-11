package com.siemens.meetingschedulersystem.service.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService {

  /*  @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.from_phone_number}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);

        Message.creator(new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        message)
                .create();
    }*/
}
