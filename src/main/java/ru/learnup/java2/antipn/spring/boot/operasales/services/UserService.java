package ru.learnup.java2.antipn.spring.boot.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.java2.antipn.spring.boot.operasales.reporsitories.UserRepository;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.User;

//Логика работы с пользователями в памяти программы

@Component
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User searchByLoginAndPass(String login, String pass) {
        final User byLogin = repository.getByLogin(login);
        return (byLogin.getPass().equals(pass)) ? byLogin : null;
    }
}

