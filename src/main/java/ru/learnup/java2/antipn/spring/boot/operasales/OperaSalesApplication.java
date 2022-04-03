package ru.learnup.java2.antipn.spring.boot.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.PublicEvent;
import ru.learnup.java2.antipn.spring.boot.operasales.services.PublicEventManagerImpl;

import java.io.IOException;

@SpringBootApplication
public class OperaSalesApplication {

    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(OperaSalesApplication.class, args);
        PublicEventManagerImpl manager = context.getBean(PublicEventManagerImpl.class);

//1) Добавлять новые премьеры
        //manager.addEventQuick(); //добавить событие быстро без ввода данных
        //manager.addEvent();   //добавить событие вручную

//2) Просматривать список всех мероприятий и какого-то конкретного (с подробным описанием) +
        manager.showEventsInDB();
        //простой поиск
        //manager.showSomeEventsInDB(eventName -> eventName.getPublicEventName().contains("8")); //Все на 8  выведется
        //сложный поиск
        //manager.showSomeEventsInDB("Щ*чик"); //Щелкунчик выведется

//4) Изменять показатели премьеры
        //manager.changeEventInDB(); //РЕДАКТИРОВАНИЕ СОБЫТИЯ!!!!

//3) Удалять мероприятия
        //manager.deleteEventInDB();

//5) Покупать билет на мероприятие
        //manager.sellTicketInDB();
//6) сдавать билеты на мероприятие
        //manager.returnTicketInDB(); // возврат билета

    }
}
