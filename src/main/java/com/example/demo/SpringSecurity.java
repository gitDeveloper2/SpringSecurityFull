package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //
                .csrf().disable()
                .authorizeRequests()
//                hasAnyAuthority("ROLE_ADMIN").
                .antMatchers("/webjars/**","/register/**","/addUser/**").permitAll() 
                .antMatchers("/home/**").hasAuthority("ROLE_USER")
                .antMatchers("/adminHome/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .successHandler(new MySuccessHandler())
                .and()
                .logout() .invalidateHttpSession(true)
                .clearAuthentication(true) .permitAll();
    }
}
