package com.example.springmvc.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Spring Web MVC 활용
 * Retention : 해당 어노테이션의 정보를 언제까지 유지할 것 인가.
 * Target : 해당 어노테이션을 사용할 수 있는 위치
 * Documented : 해당 어노테이션을 사용한 코드의 javadoc 에 어노테이션에 대한 정보를 표기한다.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET, value = "/hello")
public @interface GetHelloMapping {
}
