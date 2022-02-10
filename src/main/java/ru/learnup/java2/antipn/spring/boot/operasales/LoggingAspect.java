

package ru.learnup.java2.antipn.spring.boot.operasales;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: antipn
 * Created on 11.02.2022, 00:01
 * Description:
 */
@Component
@Aspect
public class LoggingAspect {

    @After("execution(* ru.learnup.java2.antipn.spring.boot.operasales.EventManager.sellTicket(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter() is running!");
        System.out.println("Отслеживаем работу продажи билета в методе : " + joinPoint.getSignature().getName());
        System.out.println("Письмо о продаже билета выбо выслано на почту менеджера");
    }

}