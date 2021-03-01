package com.example.springmvc.event.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Event {

    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private int limitOfEnrollment;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;

    public Event(Long id) {
        this.id = id;
    }

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
