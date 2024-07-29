package com.dk.configuration;

import com.dk.modelo.Role;
import com.dk.modelo.Security.AuthRequest;
import com.dk.segurity.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity // class will be known as web configurer
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDService;

    private final JWTTokenFilter jwtTokenFilter;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager auth) throws Exception {

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(auth);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");


        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( // requests to public and private routes
                        authorizeConfig -> {
                            // Public endpoints
                            authorizeConfig.requestMatchers("/auth/**").permitAll(); // public endpoints
                            authorizeConfig.requestMatchers("/prueba/**").permitAll(); // public endpoints
                            authorizeConfig.requestMatchers("/logout").permitAll(); // doesnt need to be authenticated
                            // authorizeConfig.requestMatchers(new AntPathRequestMatcher("/logout")).permitAll();
                            // Secured endpoints
                            authorizeConfig.requestMatchers("/admin/**").hasRole(Role.ADMIN.toString());
                            authorizeConfig.requestMatchers("/user/**").hasRole(Role.ADMIN.toString());
                            authorizeConfig.requestMatchers("/pedido/**" ).hasRole(Role.ADMIN.toString());
                            authorizeConfig.requestMatchers("/product/**" ).hasRole(Role.ADMIN.toString());
                            authorizeConfig.requestMatchers("/user/**").hasRole(Role.USER.toString());
                            authorizeConfig.requestMatchers("/pedido").hasRole(Role.USER.toString());
                            authorizeConfig.requestMatchers("/product/**" ).hasRole(Role.USER.toString());
                           //authorizeConfig.requestMatchers("/user/**").hasAuthority("USER");
                            // Any other request must be authenticated
                            authorizeConfig.anyRequest().authenticated(); // only authenticated users can perfom requests

                        }
                )

                 //.formLogin(Customizer.withDefaults()) // pantalla de inicio de sesión predeterminada de Spring Security
                 //.oauth2Login(Customizer.withDefaults()) // pantalla de inicio de sesión OAuth2 - credenciales de Google
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                //se crean sesiones
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // La sesión es stateless, útil para APIs REST
                .addFilter(jwtAuthenticationFilter)// agrego filtro de autenticacion
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)  // Agregar filtro JWT
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(this.userDService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main (String[] args){
        System.out.println("                    ======================================================================");
        System.out.println("esta es la contraseña : " +  new  BCryptPasswordEncoder().encode("agus"));
        System.out.println("                    ======================================================================");
    }
}