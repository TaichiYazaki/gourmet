package com.example.gourmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity //Spring Securityのweb用機能を利用する
@Configuration     //設定用のクラス
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
            .loginPage("/login")
            .loginProcessingUrl("/execute-login")
            .failureUrl("/login-error")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/main-page",true)
        )
            .authorizeHttpRequests(authz -> authz
            .mvcMatchers("/register","/insert","/password-reset-link","/login","/css/**", "/img/**", "/js/**", "/fonts/**").permitAll()
            .anyRequest().authenticated()
        )
            .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
        );

        return http.build();   
    }
    /**
     * パスワードのハッシュ化
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    
    public void config(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
