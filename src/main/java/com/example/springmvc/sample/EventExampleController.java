package com.example.springmvc.sample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Web MVC 매핑 연습 문제
 * PathVariable 의 value 는 플레이스 홀더 "{id}" 키값과 매핑할 값이 다를 경우 사용한다.
 */
@RestController
public class EventExampleController {

    @GetMapping("/events")
    public String events() {
        return "events";
    }

    @GetMapping("/events/{id}")
    public String event(@PathVariable("id") Long id) {
        return "events " + id;
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String eventPost() {
        return "event post";
    }

    @DeleteMapping("/events/{id}")
    public String eventDelete(@PathVariable("id") Long id) {
        return "event Delete " + id;
    }

    @PutMapping(value = "/events/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String eventUpdate(@PathVariable("id") Long id) {
        return "event Put " + id;
    }
}
