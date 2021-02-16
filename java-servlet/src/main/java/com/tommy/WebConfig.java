package com.tommy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Web Mvc
 * Java Servlet Application
 * Spring Web Mvc 연동
 */
@Configuration
// (useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Controller.class))
@ComponentScan
public class WebConfig {
}
