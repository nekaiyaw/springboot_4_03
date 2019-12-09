package com.example.springboot_4_03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.swing.*;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .access("hasAnyAuthority('User' , 'ADMIN')")
                .antMatchers("/admin").access("hasAuthority('ADMIN')"
                .anyRequest(}.authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll(;)

        @Override
        protected void configure(AuthenticationManagerBuilder auth)throws Exception {
            auth.inMemoryAuthentication().withUser("user")
                    .password(passwordEncoder().encode("password"))
                    .authorities("USER")
                    .and()
                    .withUser("dave")
                    .password(passwordEncoder().encode("begreat"))
                    .authorities("ADMIN");


        }
    }
