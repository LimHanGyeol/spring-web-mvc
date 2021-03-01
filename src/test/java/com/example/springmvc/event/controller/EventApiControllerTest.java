package com.example.springmvc.event.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventApiController.class)
class EventApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEvent() throws Exception {
        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

//    @Test
//    @DisplayName("Matrix Variable 을 사용한 요청")
//    void getEventByName() throws Exception {
//        mockMvc.perform(get("/events/1;name=spring"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("spring"));
//    }

    // 예제용 URI Pattern. 실 Post 사용시와는 맞지 않음
    @Test
    void getEventPost() throws Exception {
        mockMvc.perform(post("/events/1")
                .param("name", "springboot")
                .param("limitOfEnrollment", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("springboot"))
                .andExpect(jsonPath("$.limitOfEnrollment").value("3"));
    }

    @Test
    void getEventPost2() throws Exception {
        mockMvc.perform(post("/events")
                .param("name", "springboot")
                .param("limitOfEnrollment", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("springboot"))
                .andExpect(jsonPath("$.limitOfEnrollment").value("3"));
    }
}
