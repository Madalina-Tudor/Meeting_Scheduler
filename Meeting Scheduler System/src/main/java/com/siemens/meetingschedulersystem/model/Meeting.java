package com.siemens.meetingschedulersystem.model;

// pt defini structura întâlnirilor pachet pentru clasele modelelor, care reprezintă obiectele utilizate
// în aplicație, cum ar fi User


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity

@Table(name = "Meetings")


public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User organizer;

    @Column(nullable = false)
    private String location;


}
