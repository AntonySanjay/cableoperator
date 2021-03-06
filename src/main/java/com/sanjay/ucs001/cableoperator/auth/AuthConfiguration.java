package com.sanjay.ucs001.cableoperator.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/login";

    private final SuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(LOGIN_URL, "/register", "/customer/**", "/404", "/").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().hasRole("OPERATOR")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(LOGIN_URL)
                .and()
                .formLogin()
                .loginPage(LOGIN_URL)
                .successHandler(successHandler);
    }
}
