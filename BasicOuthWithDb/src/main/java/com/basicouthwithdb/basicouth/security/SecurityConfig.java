package com.basicouthwithdb.basicouth.security;

import com.basicouthwithdb.basicouth.model.User;
import com.basicouthwithdb.basicouth.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)).
                csrf(AbstractHttpConfigurer::disable).
                httpBasic(Customizer.withDefaults()).
                formLogin(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(x ->
                            x.requestMatchers("/public/**", "/h2-console/**", "/post/user").permitAll().
                            requestMatchers("/private").authenticated().
                            requestMatchers("/private/user").hasRole("USER").
                            requestMatchers("/private/admin").hasRole("ADMIN").
                            anyRequest().authenticated()
                        )
                .userDetailsService(userDetailsService)
                .build();
    }

}
