package ru.learnup.java2.antipn.spring.boot.operasales.reporsitories;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.User;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

//Реализация внутренней базы данных с пользователями и их ролями

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new HashMap<>();

    @PostConstruct
    public void init() {
        storage.put(
                "user",
                new User(
                        "user",
                        "user",
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_USER"))));
        storage.put(
                "admin",
                new User(
                        "admin",
                        "admin",
                        asList(
                                new SimpleGrantedAuthority("ROLE_USER"),
                                new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Override
    public User getByLogin(String login) {
        return storage.get(login);
    }

    @Override
    public User showUsers() {
        System.out.println(storage.toString());
        return null;
    }


}
