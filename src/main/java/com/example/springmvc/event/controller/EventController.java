package com.example.springmvc.event.controller;

import com.example.springmvc.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개
 */
@RequiredArgsConstructor
@Controller
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "events";
    }

}
