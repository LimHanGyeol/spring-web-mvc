package com.example.springmvc.sample;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC 활용
 * 요청 매핑하기 HTTP Method, URI Pattern, Content-Type, Accept
 * RequestMapping 어노테이션을 전역적인 URI 를 설정할 수 있다.
 * 전역으로 설정한 RequestMapping 에 Content-Type, Accept 를 설정할 경우 메서드에 선언된 내용으로 오버라이딩 된다. 권장하지 않는다.
 */
@Controller
// @RequestMapping("/hello")
public class SampleController {

    /**
     * RequestMapping 어노테이션에서 Http Method 는 {} 배열의 형태로 복수개로 설정할 수 있다.
     * consumes(Header - Content-Type) : RequestBody 의 타입이 JSON 일 경우에만 취급 하겠다.
     * produces(Header - Accept) : Response 로 특정한 타입을 원할 경우 사용한다.
     * @return "hello"
     */
    // @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @GetMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hello() {
        return "hello";
    }

//    @GetMapping("/{name:[a-z]+}")
//    @ResponseBody
//    public String helloRegex(@PathVariable String name) {
//        return "hello " + name;
//    }

}
