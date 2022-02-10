package ru.learnup.java2.antipn.spring.boot.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class OperaSalesApplication {

    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(OperaSalesApplication.class, args);
        EventManager manager = context.getBean(EventManager.class);

        PublicEvent event1 = new PublicEvent("Балет Лебединое озеро", "10-02-2022", "18:00", "18+", 100);
        PublicEvent event2 = new PublicEvent("Балет щелкунчик", "10-02-2022", "14:00", "6+", 100);
        PublicEvent event3 = new PublicEvent("Призрак Оперы", "10-02-2022", "22:00", "18+", 120);
        PublicEvent event4 = new PublicEvent("Женитьба", "11-02-2022", "17:00", "12+", 80);
        PublicEvent event5 = new PublicEvent("Слишком женатый таксист", "11-02-2022", "18:00", "18+", 60);
        PublicEvent event6 = new PublicEvent("Интимная жизнь", "12-02-2022", "19:00", "18+", 2000);

//1) Добавлять новые премьеры (с указанием названия, подробного описания, возрастной категории и количество доступных мест)
        manager.addEvent(event1);
        manager.addEvent(event2);
        manager.addEvent(event3);
        manager.addEvent(event4);
        manager.addEvent(event5);
        manager.addEvent(event6);
//2) Изменять показатели премьеры
//        manager.editEvent("Женитьба");      //РЕДАКТИРОВАНИЕ СОБЫТИЯ!!!!
//3) Удалять мероприятия
        manager.removeEvent("Призрак Оперы");

//5) Покупать билет на мероприятие и сдавать его (если вдруг надо)
        manager.sellTicket(event6);
        manager.sellTicket(event6);
        manager.sellTicket(event6);
        manager.sellTicket(event6);
        manager.returnTicket(event6); // возврат билета
        manager.showAllEvents();

//4) Просматривать список всех мероприятий и какого-то конкретного (с подробным описанием)
        manager.showAllEvents();
        //простой поиск
        manager.showSomeEvents(eventName -> eventName.getPublicEventName().contains("Б")); //Все на Б выведется
        //сложный поиск
        manager.showSomeEvents("Б*чик"); //Балет щелкунчик выведется
    }
}
