package com.inmemauth.inmemoryauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securtyFilterChain(HttpSecurity http) throws Exception{
        http.
                headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)).
                csrf(AbstractHttpConfigurer::disable).
                formLogin(Customizer.withDefaults()).
                httpBasic(Customizer.withDefaults()).
                authorizeHttpRequests(req -> req.
                        requestMatchers("/public/**").permitAll().
                        requestMatchers("/private/**").hasAnyRole("ADMIN", "USER").
                        anyRequest().authenticated()
                );

        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder().
                username("user").
                password("password").
                passwordEncoder(passwordEncoder()::encode).
                roles("USER").
                build();

        UserDetails adminDetails = User.builder()
                .username("admin")
                .password("password")
                .passwordEncoder(passwordEncoder()::encode)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails, adminDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
