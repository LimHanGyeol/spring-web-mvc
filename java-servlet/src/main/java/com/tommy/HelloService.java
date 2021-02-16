package com.tommy;

import org.springframework.stereotype.Service;

/**
 * Spring Web Mvc
 * Spring IoC Container 연동
 */
@Service
public class HelloService {

    public String getName() {
        return "hangyeol";
    }
}
