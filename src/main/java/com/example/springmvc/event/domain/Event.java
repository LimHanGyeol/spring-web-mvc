package com.example.springmvc.event.domain;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개
 */
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    private Long id;

    @NotBlank
    private String name;

    @Min(0)
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
    public Event(Long id, String name, int limitOfEnrollment) {
        this.id = id;
        this.name = name;
        this.limitOfEnrollment = limitOfEnrollment;
    }

}
