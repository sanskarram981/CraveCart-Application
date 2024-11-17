package com.projects.cravecart.configuration;

import com.projects.cravecart.interceptor.MyCustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyCustomWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register");
    }
}
