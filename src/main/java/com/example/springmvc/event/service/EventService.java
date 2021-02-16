package com.example.springmvc.event.service;

import com.example.springmvc.event.domain.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개
 * 높은 결합도와 낮은 의존도
 */
@Service
public class EventService {

    public List<Event> getEvents() {
        Event event1 = Event.builder()
                .name("스프링 웹 MVC 스터디 1차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2020,3,20,13,30,0))
                .endDateTime(LocalDateTime.of(2020,3,20,17,30,0))
                .build();

        Event event2 = Event.builder()
                .name("스프링 웹 MVC 스터디 2차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2020,3,27,13,30,0))
                .endDateTime(LocalDateTime.of(2020,3,27,17,30,0))
                .build();

        return List.of(event1, event2);
                
    }
}
