package com.example.springmvc.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter, Domain Class Converter
 */
//@WebMvcTest(SampleController.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void hello() throws Exception {
        Person person = new Person("hangyeol");
        Person savedPerson = personRepository.save(person);

        mockMvc.perform(get("/hello/" + savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello hangyeol"));
    }

    @Test
    void hello2() throws Exception {
        Person person = new Person("hangyeol");
        Person savedPerson = personRepository.save(person);

        mockMvc.perform(get("/user")
                .param("id", savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello hangyeol"));
    }

    @Test
    void helloStatic() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello index")));
    }

    @Test
    void helloMobileStatic() throws Exception {
        mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    @DisplayName("RequestBody String Test")
    void stringMessageRequestBody() throws Exception {
        mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    @DisplayName("RequestBody Json Test")
    void personJsonMessageRequestBody() throws Exception {
        Person person = new Person(1L, "hangyeol");
        String jsonString = objectMapper.writeValueAsString(person);

        mockMvc.perform(get("/jsonMessage")
                .contentType(MediaType.APPLICATION_JSON) // 내가 요청으로 보내는 데이터의 타입
                .accept(MediaType.APPLICATION_JSON) // 요청에 대한 응답으로 해당 타입의 데이터를 원한다.
                .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
