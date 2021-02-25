package com.example.springmvc.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter, HTTP Message Converter
 */
@RestController
public class SampleController {

    @GetMapping("/hello/{id}")
    public ResponseEntity<String> hello(@PathVariable("id") Person person) {
        return ResponseEntity.ok().body(String.format("hello %s", person.getName()));
    }

    @GetMapping("/user")
    public ResponseEntity<String> hello2(@RequestParam("id") Person person) {
        return ResponseEntity.ok().body(String.format("hello %s", person.getName()));
    }

    @GetMapping("/message")
    public ResponseEntity<String> message(@RequestBody String body) {
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/jsonMessage")
    public ResponseEntity<Person> jsonMessage(@RequestBody Person person) {
        return ResponseEntity.ok().body(person);
    }

}
