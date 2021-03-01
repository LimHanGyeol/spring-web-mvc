package com.example.springmvc.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventExampleController.class)
class EventExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void getEvents() throws Exception {
//        mockMvc.perform(get("/events"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("events"));
//    }
//
//    @Test
//    void getEventById() throws Exception {
//        mockMvc.perform(get("/events/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("events 1"));
//    }
//
//    @Test
//    void createEvent() throws Exception {
//        mockMvc.perform(post("/events")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("event post"));
//    }
//
//    @Test
//    void deleteEvent() throws Exception {
//        mockMvc.perform(delete("/events/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("event Delete 1"));
//    }
//
//    @Test
//    void updateEvent() throws Exception {
//        mockMvc.perform(put("/events/1")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("event Put " + 1));
//    }

}
