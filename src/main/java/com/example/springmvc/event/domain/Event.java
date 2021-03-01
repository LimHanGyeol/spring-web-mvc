package com.example.springmvc.event.domain;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    private Long id;
    private String name;
    private int limitOfEnrollment;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Builder
    private Event(Long id, String name, int limitOfEnrollment, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = id;
        this.name = name;
        this.limitOfEnrollment = limitOfEnrollment;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Builder
    private Event(Long id, String name, int limitOfEnrollment) {
        this.id = id;
        this.name = name;
        this.limitOfEnrollment = limitOfEnrollment;
    }
}
