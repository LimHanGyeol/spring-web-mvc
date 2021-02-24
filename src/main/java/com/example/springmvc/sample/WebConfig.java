package com.example.springmvc.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter
 * Spring Boot 에서는 이런 설정 클래스를 만들지 않아도 된다.
 * Formatter 를 Bean 으로 만들면 자동으로 Formatter 추가를 해준다.
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new PersonFormatter());
    }
}
