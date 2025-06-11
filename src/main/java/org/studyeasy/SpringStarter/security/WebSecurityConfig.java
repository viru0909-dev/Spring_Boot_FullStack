package org.studyeasy.SpringStarter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public static PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder(); 
    }
    @Bean
    public  SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
        .authorizeRequests()
        .antMatchers(WHITELIST)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/",true)
        .failureUrl("/login?error")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .and()
        .httpBasic();

        //TODO: remove these after upgrading the DB from H2 infile DB
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
       return  http.build();
    }
}
