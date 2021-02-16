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

    private String name;
    private int limitOfEnrollment;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Builder
    private Event(String name, int limitOfEnrollment, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.limitOfEnrollment = limitOfEnrollment;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
