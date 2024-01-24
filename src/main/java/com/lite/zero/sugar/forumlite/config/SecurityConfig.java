package com.lite.zero.sugar.forumlite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private CustomUserDetailService userDetailService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/register/**", "/css/**", "/js/**", "/assets/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/api/forum")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .build();
    }

//    Метод configure(AuthenticationManagerBuilder builder) не выделяется аннотацией @Bean или другой аннотацией,
//    потому что он не создает новый объект, а настраивает существующий.
//
//    Аннотация @Bean используется для создания нового объекта, который затем может быть использован в других компонентах приложения.
//    Но в случае метода configure(AuthenticationManagerBuilder builder), мы не создаем новый объект,
//    а настраиваем существующий объект AuthenticationManagerBuilder.
//
//    Кроме того, метод configure(AuthenticationManagerBuilder builder) вызывается автоматически при инициализации Spring Security,
//    поэтому нет необходимости помечать его аннотацией @Bean или другой аннотацией.

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
