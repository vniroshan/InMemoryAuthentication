package com.niroshan.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("niro").password("{noop}niro").authorities("ADMIN");
        auth.inMemoryAuthentication().withUser("ravi").password("{noop}ravi").authorities("EMPLOYEE");
        auth.inMemoryAuthentication().withUser("viji").password("{noop}viji").authorities("STUDENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //URL-AccessType
    http.authorizeRequests()
            .antMatchers("/home").permitAll()
            .antMatchers("/welcome").authenticated()
            .antMatchers("/admin").hasAuthority("ADMIN")
            .antMatchers("/emp").hasAuthority("EMPLOYEE")
            .antMatchers("/std").hasAuthority("STUDENT")
        //LoginForm Details
           .and()
            .formLogin()
            .defaultSuccessUrl("/welcome",true)
        //Logout Details
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        //exception Details
        .and()
    .exceptionHandling()
    .accessDeniedPage("/denied")
        ;
    }
}
