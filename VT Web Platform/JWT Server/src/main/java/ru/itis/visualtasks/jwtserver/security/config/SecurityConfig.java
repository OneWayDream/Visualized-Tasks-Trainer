package ru.itis.visualtasks.jwtserver.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import ru.itis.visualtasks.jwtserver.exceptions.handlers.ExceptionHandlerFilter;
import ru.itis.visualtasks.jwtserver.security.filters.JwtCheckingFilter;
import ru.itis.visualtasks.jwtserver.security.filters.JwtLogoutFilter;
import ru.itis.visualtasks.jwtserver.security.filters.TokenAuthenticationFilter;
import ru.itis.visualtasks.jwtserver.security.providers.TokenAuthenticationProvider;

@Configuration
@EnableWebSecurity()
//@EnableMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Order(1)
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final JwtCheckingFilter jwtCheckingFilter;
    private final JwtLogoutFilter jwtLogoutFilter;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtCheckingFilter, TokenAuthenticationFilter.class);
        http.addFilterBefore(jwtLogoutFilter, TokenAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, CorsFilter.class);
        http.authenticationManager(authenticationManager(http));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        authenticationManagerBuilder.authenticationProvider(tokenAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

}
