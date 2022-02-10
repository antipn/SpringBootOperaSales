package ru.learnup.java2.antipn.spring.boot.operasales;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: antipn
 * Created on 09.02.2022, 21:13
 * Description:
 */

@Component
//менеджер работы с премьерами будет в единственном числе
//будет добавлять, удалять и изменять премьеры
//также будет продавать и возращать билеты
public class EventManager {
    private List<PublicEvent> eventList = new ArrayList<>();
    private Logger mylogger;

    public EventManager(Logger mylogger) {
        this.mylogger = mylogger;
    }

    //вывод всех событий на экран
    public void showAllEvents() {
        mylogger.printing();
        System.out.println("Перечень событий: ");
        for (PublicEvent event : eventList) {
            System.out.println(event);
        }
    }

    //вывод событий по имени
    public List<PublicEvent> showSomeEvents(Predicate<PublicEvent> criteria) {
        System.out.println("Вывод событий по критерию имени");
        List<PublicEvent> result = new ArrayList<>();
        for (PublicEvent event : eventList) {
            if (criteria.test(event)) {
                System.out.println(event);
                result.add(event);
            }

        }

        return result;
    }

    public List<PublicEvent> showSomeEvents(String input) {
        return showSomeEvents(new Predicate<PublicEvent>() {
            @Override
            public boolean test(PublicEvent event) {
                return event.getPublicEventName().matches(input.replace("*", ".*"));
            }
        });
    }

    //добавление события
    public List<PublicEvent> addEvent(PublicEvent event) {
        eventList.add(event);
        return eventList;
    }

    //удаление события по имени события
     public List<PublicEvent> removeEvent(String nameEvent) {
        PublicEvent eventForRemove = null;
        for (PublicEvent event : eventList) {
            if (event.getPublicEventName().equals(nameEvent)) {
                eventForRemove = event;
            }
        }
        if (!(eventForRemove == null)) {
            eventList.remove(eventForRemove);
        }
        return eventList;
    }

    // редактирование события
    public List<PublicEvent> editEvent(String eventName) throws IOException {
        PublicEvent eventForEdit = null;
        for (PublicEvent event : eventList) {
            if (event.getPublicEventName().equals(eventName)) {
                eventForEdit = event;
            }
        }
        if (!(eventForEdit == null)) {
            eventList.remove(eventForEdit); //удалили событие

            String eventEditedName = eventForEdit.getPublicEventName();
            String eventEditedStartDate = eventForEdit.getPublicEventStartDate();
            String eventEditedStartTime = eventForEdit.getPublicEventStartTime();
            String eventEditedRatingRARS = eventForEdit.getRatingRARS();
            int eventEditedTicketsCount = eventForEdit.getTicketsCount();

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Изменяемое событие " + eventForEdit);
            Boolean changeProcess = true;
            while (changeProcess) {
                System.out.println("Выберте что нужно изменить: 1 - Имя события, 2 - Дату, 3 - Время, 4 -  RARS, 5 - Кол-во билетов , 0 - завершить редактирование");
                switch (Integer.parseInt(input.readLine())) {
                    case (1):
                        System.out.println("Предыдущее значение " + eventEditedName + " введите новое название события:");
                        eventEditedName = input.readLine();
                        System.out.println("Изменение принято");
                        break;
                    case (2):
                        System.out.println("Предыдущее значение " + eventEditedStartDate + " введите новоую дату события:");
                        eventEditedStartDate = input.readLine();
                        System.out.println("Изменение принято");
                        break;
                    case (3):
                        System.out.println("Предыдущее значение " + eventEditedStartTime + " введите новое время события:");
                        eventEditedStartTime = input.readLine();
                        System.out.println("Изменение принято");
                        break;
                    case (4):
                        System.out.println("Предыдущее значение " + eventEditedRatingRARS + " введите возврастной рейтинг:");
                        eventEditedRatingRARS = input.readLine();
                        System.out.println("Изменение принято");
                        break;
                    case (5):
                        System.out.println("Предыдущее значение " + eventEditedTicketsCount + " введите новое кол-во билетов:");
                        eventEditedTicketsCount = Integer.parseInt(input.readLine());
                        System.out.println("Изменение принято");
                        break;
                    case (0):
                        changeProcess = false;
                        System.out.println("Закончили менять событие.");
                        input.close();
                        break;
                }

            }

            eventList.add(new PublicEvent(eventEditedName, eventEditedStartDate, eventEditedStartTime, eventEditedRatingRARS, eventEditedTicketsCount)); //добавили измененное событие
        }
        return eventList;
    }

    public void sellTicket(PublicEvent event) {
// System.out.println("Метод продажи билета на мероприятие");
// проверить наличие свободных мест еслиесть то увеличить проданных билетов
// если нет билетов для продажи то выкинуть ошибку
        if (event.getSoldTicketsCount() < event.getTicketsCount()) {// если еще есть билеты всего выпущенного меньше проданных
            event.increaseSoldTicket();
// System.out.println("Билет на мероприятие " +event.toString() + "\nуспешно продан");//добавляем ++ к числу проданных билетов
        } else {
            System.out.println("Нет свободных билетов");
        }
    }

    public void returnTicket(PublicEvent event) {
// System.out.println("Метод возврата билета на мероприятие " + event.toString());
// если есть проданные билеты то уменьшаем счетчик иначе выкидываем ошибку
        if (event.getSoldTicketsCount() > 0) {
            event.decreaseSoldTicket();
// System.out.println("Билет успешно возращен");
        } else {
            System.out.println("Нет купленных билетов");

        }
    }
}