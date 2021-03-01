package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Spring Web MVC 활용
 * Handler Method URI Pattern, Request Parameters, Request HTML Form Submit
 */
@RequiredArgsConstructor
@RestController
public class EventApiController {

    Logger log = LoggerFactory.getLogger(EventApiController.class);

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

    // @RequestParam String name, @RequestParam int limitOfEnrollment
    // 객체 필드의 타입이 맞지 않을 경우 BindingException 이 발생하는데,
    // BindingResult 를 이용해 에러를 담고, 요청은 진행하게 할 수 있다.
    // BindingResult 의 상위타입인 Errors 가 있지만 BindingResult 가 사용할 수 있는 인터페이스가 더 많아 BindingResult 를 사용한다.
    // 담긴 ModelAttribute 에 Valid 를 하면 Valid 에서 걸러진 에러도 BindingResult 에 담기게 된다.
    @PostMapping("/events")
        public Event create(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("------------ Error ------------");
            bindingResult.getAllErrors().forEach(error -> {
                log.error("{}", error);
            });
        }
        return event;
    }

}
