package ru.learnup.java2.antipn.spring.boot.operasales.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()

                //продажа билета только пользователям
                // sell-{event-id}/{ticket-seat}
                // http://localhost:8080/app/v1/events/sell-1/1
                //http://localhost:8080/app/v1/events/sell-3/available
                .antMatchers(HttpMethod.POST, "/app/v1/events/sell-*/*").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/app/v1/events/sell-*/available").hasRole("USER")

                // удалять, изменять, добавлять событие только ADMIN
                // http://localhost:8080/app/v1/events/event/delete-1
                // http://localhost:8080/app/v1/events/event/update-5
                // http://localhost:8080/app/v1/events/event/new
                .antMatchers(HttpMethod.DELETE, "/app/v1/events/event/delete-*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/app/v1/events/event/update-*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/app/v1/events/event/new").hasRole("ADMIN")

                // возврат билетов только ADMIN /return/ticket-{id}
                // http://localhost:8080/app/v1/events/return/ticket-8
                .antMatchers(HttpMethod.PUT, "/app/v1/events/return/ticket-*").hasRole("ADMIN")

                // просмотр всех событий или одного события по id для всех
                // просмотр билетов на событие по id тоже для всех
                //просмотр вообще всех билетов
                //http://localhost:8080/app/v1/events/view-all
                //http://localhost:8080/app/v1/events/view-1
                //http://localhost:8080/app/v1/events/view-1/tickets
                //http://localhost:8080/app/v1/tickets
                .antMatchers("/app/v1/events/view-all").permitAll()
                .antMatchers("/app/v1/events/view-*").permitAll()
                .antMatchers("/app/v1/events/view-*/tickets").permitAll()
                .antMatchers("/app/v1/tickets").permitAll()


                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();
                //.formLogin(); //пользуюсь POSTMAN для запросов, в браузере только GET
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}
