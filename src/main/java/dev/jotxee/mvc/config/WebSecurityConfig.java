package dev.jotxee.mvc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final LoginSuccessHandler successHandler;

    public static final String[] ENDPOINTS_WHITELIST = {"/css/**", "/js/**", "/images/**", "/about",
            "/login", "/register", "/favicon.ico", "/error"};

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build());

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
            .formLogin(formLogin -> formLogin
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .successHandler(successHandler))
            .logout(logout -> logout
                    .logoutSuccessUrl("/login"))

            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/monthly-payments/**")).hasRole("USER")
                    .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated())
            .exceptionHandling(handling -> handling
                    .accessDeniedPage("/error_403"));
        return http.build();
    }
}
