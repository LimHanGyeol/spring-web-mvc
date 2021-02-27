package com.example.springmvc.sample;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC 활용
 * 요청 매핑하기 HTTP Method, URI Pattern, Content-Type, Accept, Headers
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
     * headers : ! 연산자를 통해 반례의 상황을 만들 수 있다. ("!" + HttpHeaders.FROM)
     * headers : 문자열 연산을 통해 header 와 key/value 형태를 만들어 사용할 수 있다. (HttpHeaders.AUTHORIZATION + "=" + "111")
     * params : params = "name" 등의 사용을 통해 요청 파라미터를 검증할 수 있다.
     * params : 문자열 연산을 통해 header 와 key/value 형태를 만들어 사용할 수 있다. (params = "name=hangyeol")
     *
     * @return "hello"
     */
    // @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @GetMapping(value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = HttpHeaders.FROM,
            params = "name")
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
