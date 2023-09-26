package com.fours.tolevelup.configuration.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CORSFilter {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);       // 내 서버가 응답할 때 JSON을 js에서 처리할 수 있게 할 지 설정
        config.addAllowedOrigin("*");           // 모든 ip의 응답을 허용
        config.addAllowedHeader("*");           // 모든 geader에 응답을 허용
        config.addAllowedMethod("*");           // 모든 Method(post, get, put, delete, patch) 요청 허용
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }

}
