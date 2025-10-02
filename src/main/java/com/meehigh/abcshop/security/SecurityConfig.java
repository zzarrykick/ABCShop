/*
package com.meehigh.abcshop.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        // HttpSecurity - builderul unde expui ce rute sunt libere / protejate
        http.authorizeHttpRequests( auth-> auth.
                // authorizeHttpRequests - sectiunea de autorizate pe rute
                requestMatchers("/","/home").permitAll() // ruta /home si / este publica
                .requestMatchers("/view/products/add", "/view/products/edit/**", "/view/products/delete/**").authenticated() // ruta /products/add cere user logat

                .anyRequest().authenticated() // orice alta ruta necesita logare
        ).formLogin(form-> form.
                defaultSuccessUrl("/home",true))
        .logout(logout -> logout.logoutSuccessUrl("/home")
                        .permitAll()
        );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        var user = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .build();

        var admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
        */