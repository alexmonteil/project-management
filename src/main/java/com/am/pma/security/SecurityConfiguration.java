package com.am.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    ProjectIOAuthenticationFailureHandler projectIOAuthenticationFailureHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requiresChannel().requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure();
        http.authorizeRequests()
                .antMatchers("/dashboard").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects").authenticated()
                .antMatchers("/projects/new").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects/create").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects/save").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects/update").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects/delete").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/projects/timelines").authenticated()
                .antMatchers("/projects/details").authenticated()
                .antMatchers("/projects/my-projects").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/employees").permitAll()//.authenticated()
                .antMatchers("/employees/new").permitAll()//.hasRole("ADMIN")
                .antMatchers("/employees/create").permitAll()//.hasRole("ADMIN")
                .antMatchers("/employees/update").permitAll()//.authenticated()
                .antMatchers("/employees/save").permitAll()//.authenticated()
                .antMatchers("/employees/delete").permitAll()//.hasRole("ADMIN")
                .antMatchers("/employees/edit-my-profile").permitAll()//.authenticated()
                .antMatchers("/employees/profile").permitAll()//.authenticated()
                .antMatchers("/messages/new").authenticated()
                .antMatchers("/messages/create").authenticated()
                .antMatchers("/messages/delete").authenticated()
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .failureHandler(projectIOAuthenticationFailureHandler);
    }

}
