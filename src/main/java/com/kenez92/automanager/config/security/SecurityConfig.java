package com.kenez92.automanager.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                .antMatchers(HttpMethod.GET, "/cars/*").authenticated()
                .antMatchers(HttpMethod.GET, "/users/createUser").permitAll()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/basicAuth/login")
                .and().csrf().disable();
    }
}
