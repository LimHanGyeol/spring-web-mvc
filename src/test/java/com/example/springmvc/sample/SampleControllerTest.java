package com.example.springmvc.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring MVC 활용
 * 요청 매핑하기 HTTP Method, URI Pattern, Content-Type, Accept, Headers
 */
@WebMvcTest(SampleController.class)
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.FROM, "localhost")
                .param("name", "hangyeol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    void invalid_hello() throws Exception {
        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
        // .andExpect(handler().handlerType(SampleController.class))
        // .andExpect(handler().methodName("hello"));
    }

//    @Test
//    void hello_regex() throws Exception {
//        mockMvc.perform(get("/hangyeol"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello hangyeol"));
//    }

}
