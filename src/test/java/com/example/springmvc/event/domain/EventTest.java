package com.example.springmvc.event.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    void event_create() {
        Event event = Event.builder()
                .name("스프링 웹 MVC 스터디 1차")
                .limitOfEnrollment(5)
                .startDateTime(LocalDateTime.of(2020, 3, 20, 13, 30, 0))
                .endDateTime(LocalDateTime.of(2020, 3, 20, 17, 30, 0))
                .build();

        assertThat(event).isNotNull();
        assertThat(event.getName()).isEqualTo("스프링 웹 MVC 스터디 1차");
    }

}
