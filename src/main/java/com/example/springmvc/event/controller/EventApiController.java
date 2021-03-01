package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Web MVC 활용
 * Handler Method URI Pattern
 */
@RequiredArgsConstructor
@RestController
public class EventApiController {

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable long id, @MatrixVariable String name) {
        return Event.builder()
                .id(id)
                .name(name)
                .limitOfEnrollment(5)
                .build();
    }

}
