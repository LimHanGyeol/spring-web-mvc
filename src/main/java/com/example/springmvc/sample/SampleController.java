package com.example.springmvc.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter
 */
@RestController
public class SampleController {

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> hello(@PathVariable("name") Person person) {
        return ResponseEntity.ok().body(String.format("hello %s", person.getName()));
    }

    @GetMapping("/user")
    public ResponseEntity<String> hello2(@RequestParam("id") Person person) {
        return ResponseEntity.ok().body(String.format("hello %s", person.getName()));
    }

}
