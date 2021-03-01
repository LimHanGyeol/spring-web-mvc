package com.example.springmvc.event.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("EventForm View Test")
    void getEventForm() throws Exception {
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()));
    }

    @Test
    @DisplayName("유효하지 않은 데이터가 포함되어 Valid 에 걸려 BindingResult 에 Error 가 담길 경우")
    void invalidPostEvent() throws Exception {
        mockMvc.perform(post("/events")
                .param("name", "spring")
                .param("limitOfEnrollment", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());
    }
}
