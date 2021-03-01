package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Web MVC 활용
 * Handler Method URI Pattern, Request Parameters, Request HTML Form Submit
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
     * 예제 코드용 핸들러. 일반적인 Post URI Pattern 으로 사용되지 않는다.
     */
    @PostMapping("/events/{id}")
    public Event getEventByName(@PathVariable long id,
                                @RequestParam(required = false, defaultValue = "spring") String name,
                                @RequestParam int limitOfEnrollment) {
        return Event.builder()
                .id(id)
                .name(name)
                .limitOfEnrollment(limitOfEnrollment)
                .build();
    }

    @PostMapping("/events")
    public Event create(@RequestParam String name,
                        @RequestParam int limitOfEnrollment) {
        return Event.builder()
                .id(1L)
                .name(name)
                .limitOfEnrollment(limitOfEnrollment)
                .build();
    }

}
