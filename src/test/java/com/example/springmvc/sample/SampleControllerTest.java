package com.example.springmvc.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter
 */
//@WebMvcTest(SampleController.class)
@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello/hangyeol"))
                .andDo(print())
                .andExpect(content().string("hello hangyeol"));
    }

    @Test
    void hello2() throws Exception {
        mockMvc.perform(get("/user")
                .param("name", "hangyeol"))
                .andDo(print())
                .andExpect(content().string("hello hangyeol"));
    }
}
