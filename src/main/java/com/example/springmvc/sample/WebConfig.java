package com.example.springmvc.sample;

import com.example.springmvc.config.AnotherInterceptor;
import com.example.springmvc.config.GreetingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter
 * Spring Boot 에서는 이런 설정 클래스를 만들지 않아도 된다.
 * Formatter 를 Bean 으로 만들면 자동으로 Formatter 추가를 해준다.
 * Interceptor 의 Order 를 정해주지 않으면 선언한 순서대로 적용이 된다.
 * Order 의 우선순위는 마이너스일 수록 높다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hello/**")
                .order(-1);
    }
}
