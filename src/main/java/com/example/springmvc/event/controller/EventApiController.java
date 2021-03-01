package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Web MVC 활용
 * Handler Method URI Pattern
 */
@RequiredArgsConstructor
@RestController
public class EventApiController {

    //public Event getEvent(@PathVariable long id, @MatrixVariable String name) {
    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable long id) {
        return Event.builder()
                .id(id)
                .name("springboot")
                .limitOfEnrollment(5)
                .build();
    }

    /**
     * Request Parameter 는 Map<String, String> 으로 모든 파라미터를 받아올 수 있다.
     */
    @PostMapping("/events/{id}")
    public Event getEventByName(@PathVariable long id,
                                @RequestParam(required = false, defaultValue = "spring") String name,
                                @RequestParam int limit) {
        return Event.builder()
                .id(id)
                .name(name)
                .limitOfEnrollment(limit)
                .build();
    }

}
