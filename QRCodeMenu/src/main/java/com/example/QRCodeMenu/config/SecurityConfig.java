//package com.example.QRCodeMenu.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity @Configuration @RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception   {
//
//        http
//                .httpBasic().and().authorizeRequests().antMatchers("/api/categories").hasRole("admin")
//                .and().csrf().disable().formLogin();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//
//        authenticationManagerBuilder.inMemoryAuthentication()
//
//                .withUser("mert.915@hotmail.com").password("{noop}mal").roles("admin");
//
//    }
//    }

