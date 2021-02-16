package com.tommy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Web Mvc
 * Java Servlet Application
 * Spring Web Mvc 연동
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello" + helloService.getName();
    }
}
