package ru.learnup.java2.antipn.spring.boot.operasales;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: antipn
 * Created on 10.02.2022, 17:39
 * Description:
 */

@Component
public class Logger {
    public void printing(){
        System.out.println("Логгер активирован");
    }
}
