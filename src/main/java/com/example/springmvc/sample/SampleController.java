package com.example.springmvc.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC 활용
 * 요청 매핑하기 HTTP Method, URI Pattern
 */
@Controller
// @RequestMapping("/hello") 전역적인 URI를 설정할 수 있다.
public class SampleController {

    /**
     * RequestMapping 어노테이션에서 Http Method 는 {} 배열의 형태로 복수개로 설정할 수 있다.
     * @return "hello"
     */
    // @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/{name:[a-z]+}")
    @ResponseBody
    public String helloRegex(@PathVariable String name) {
        return "hello " + name;
    }

}
