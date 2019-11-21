package com.ioproj.niekopce.Security;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Configuration
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {


    /*
    Wszystkie zasoby dostępne bez konieczności logowania się
     */
    private static final String[] WHITELIST = {
            "/js/**",
            "/css/**",
            "/images/**",
            "/webjars/**",
            "/main/*",
            "/user/**",
            "/favicon"
    };

    private static final String MAINPAGE = "/main/mainPage";
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;



    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl(MAINPAGE, true)
                .permitAll()
              //  .and()
              //  .logout().logoutSuccessHandler(customizeLogoutSuccessHandler)
                .permitAll();

    }
}
