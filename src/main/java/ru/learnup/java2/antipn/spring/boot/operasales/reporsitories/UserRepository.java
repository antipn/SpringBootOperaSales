package ru.learnup.java2.antipn.spring.boot.operasales.reporsitories;

import ru.learnup.java2.antipn.spring.boot.operasales.entity.User;

// Интерфейс пользователя который будет реализован в памяти

public interface UserRepository {
    User getByLogin(String login);
    User showUsers();
}
