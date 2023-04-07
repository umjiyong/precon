package com.gdh.precon.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class Webconfig implements WebMvcConfigurer {


//    private final HandlerInterceptor validationInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(validationInterceptor)
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/v2/api-docs")
//                .excludePathPatterns("/webjars/**")
////                .excludePathPatterns("/**")
//        ;
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .maxAge(3000);
    }
}
