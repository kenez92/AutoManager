package com.kenez92.automanager.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final BasicAuthAuthenticationProvider basicAuthAuthenticationProvider;

    SecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder, BasicAuthAuthenticationProvider basicAuthAuthenticationProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.basicAuthAuthenticationProvider = basicAuthAuthenticationProvider;
    }

    void configureAuthenticationManager() {
        authenticationManagerBuilder.authenticationProvider(basicAuthAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/car/*").authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and().headers().disable();
    }
}
