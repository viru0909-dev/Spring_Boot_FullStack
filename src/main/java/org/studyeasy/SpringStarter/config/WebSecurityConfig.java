package org.studyeasy.SpringStarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class WebSecurityConfig {
    private static final String[] WHITELIST ={
      "/",
      "/login",
      "/register",
      "/db-console/**",
      "/css/**",
      "/fonts/**",
      "/images/**",
      "/js/**"
    };
    @Bean
  
    public  SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
        .authorizeRequests()
        .antMatchers(WHITELIST)
        .permitAll()
        .anyRequest()
        .authenticated();

        //TODO: remove these after upgrading the DB from H2 infile DB
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
       return  http.build();
    }
}
