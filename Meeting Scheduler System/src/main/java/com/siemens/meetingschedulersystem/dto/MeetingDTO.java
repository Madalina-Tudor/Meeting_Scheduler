package com.siemens.meetingschedulersystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@NoArgsConstructor
@Getter
public class MeetingDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long organizerId;
    private String location;
    String organizerEmail;


    // constructori, getteri și setteri

}
