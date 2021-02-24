package com.example.springmvc.config;

import com.example.springmvc.config.AnotherInterceptor;
import com.example.springmvc.config.GreetingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .resourceChain(true); // 캐쉬를 사용할지 말지. 운영중이라면 true, 개발중이라면 false
                // .addResolver() // 어떤 요청에 해당하는 Resource 탐색
                // .addTransformer() // 응답으로 내보낼 리소스를 변경하는 방법
    }
}
