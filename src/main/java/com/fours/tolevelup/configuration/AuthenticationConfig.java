package com.fours.tolevelup.configuration;



import com.fours.tolevelup.configuration.filter.CORSFilter;
import com.fours.tolevelup.configuration.filter.JwtTokenFilter;
import com.fours.tolevelup.exception.CustomAuthenticationEntryPoint;
import com.fours.tolevelup.service.user.UserServiceImpl;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserServiceImpl userService;
    @Value("${jwt.secret-key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/*/users/join","/api/*/users/login","/api/*/users/my")
                .permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new CORSFilter().corsFilter())
                .addFilterBefore(new JwtTokenFilter(key,userService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
        return http.build();
    }
}
