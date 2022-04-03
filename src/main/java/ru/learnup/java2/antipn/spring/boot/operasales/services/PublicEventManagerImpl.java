package ru.learnup.java2.antipn.spring.boot.operasales.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.PublicEvent;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.Ticket;
import ru.learnup.java2.antipn.spring.boot.operasales.loggers.Logger;
import ru.learnup.java2.antipn.spring.boot.operasales.reporsitories.EventRepository;
import ru.learnup.java2.antipn.spring.boot.operasales.reporsitories.TicketRepository;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: antipn
 * Created on 09.02.2022, 21:13
 * Description:
 */

@Component
//у нас тут описана логика работы с базой данной! это у нас сервис
//менеджер работы с премьерами будет в единственном числе
//будет добавлять, удалять и изменять премьеры
//также будет продавать и возвращать билеты
public class PublicEventManagerImpl implements PublicEventManager {
    private List<PublicEvent> eventList = new ArrayList<>();
    private Logger mylogger;

    private EventRepository eventRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public PublicEventManagerImpl(Logger mylogger, EventRepository eventRepository, TicketRepository ticketRepository) {
        this.mylogger = mylogger;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    //repositories level

    public Collection<PublicEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    public Boolean deleteEventByIdINDB(int id) {
        eventRepository.deleteById(id);
        return true;
    }

    public Collection<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // 1  CREATE EVENT TO DATABASE FROM CONSOLE
    public static PublicEvent createEvent() throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input event name: ");
        String eventName = input.readLine();
        System.out.println("Input start date on event: ");
        String startDateEvent = input.readLine();
        System.out.println("Input start time of event: ");
        String startTimeEvent = input.readLine();
        System.out.println("Input number of hall for event: ");
        int numberHall = Integer.parseInt(input.readLine());
        System.out.println("Input RARs: ");
        String rars = input.readLine();
        System.out.println("Input tickets count for event: ");
        int ticketsCount = Integer.parseInt(input.readLine());
        PublicEvent event = new PublicEvent(null, eventName, startDateEvent, startTimeEvent, numberHall, rars, ticketsCount);
        return event;
    }

    // 1.1- ADD EVENT TO DATABASE INPUT DATA FROM CONSOLE
    public void addEvent() throws IOException {
        PublicEvent event = PublicEventManagerImpl.createEvent();
        if (event != null) {
            List<Ticket> tickets = new ArrayList<>();
            for (int i = 1; i <= event.getTicketsIssued(); i++) {
                tickets.add(new Ticket(null, event, i, false));
            }
            event.setTickets(tickets);
            event = eventRepository.save(event);
            System.out.println(event);
        }
    }

    // 1.2 - ADD EVENT TO DATABASE NO INPUT DATA FROM CONSOLE! QUICK SOLUTION
    public void addEventQuick() {
        PublicEvent event = new PublicEvent(null, "8March", "20-03-22", "11:00", 1, "6+", 4);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= event.getTicketsIssued(); i++) {
            tickets.add(new Ticket(null, event, i, false));
        }
        event.setTickets(tickets);
        event = eventRepository.save(event);
        System.out.println(event);
    }

    // 2 - SHOW ALL EVENTS TO CONSOLE
    public void showEventsInDB() {
        List<PublicEvent> events = eventRepository.findAll();

        for (PublicEvent event : events) {
            System.out.println(event);
        }
    }

    //вывод событий по имени
    public List<PublicEvent> showSomeEventsInDB(Predicate<PublicEvent> criteria) {
        System.out.println("Вывод событий по критерию имени");
        List<PublicEvent> result = new ArrayList<>();

        for (PublicEvent event : eventRepository.findAll()) {
            if (criteria.test(event)) {
                System.out.println(event);
                result.add(event);
            }

        }

        return result;
    }

    public List<PublicEvent> showSomeEventsInDB(String input) {
        return showSomeEventsInDB(new Predicate<PublicEvent>() {
            @Override
            public boolean test(PublicEvent event) {
                return event.getPublicEventName().matches(input.replace("*", ".*"));
            }
        });
    }

    // 3- DELETE EVENT FROM DATABASE BY ID
    public void deleteEventInDB() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        showEventsInDB();
        System.out.println("Input ID for deleting event: ");
        Integer inputID = Integer.parseInt(input.readLine());
        if (eventRepository.findById(inputID).isPresent()) { //улучшить код чтобы дважды не ходить в базу!!! переписать!!! через переменную
            Optional<PublicEvent> event = eventRepository.findById(inputID);
            eventRepository.deleteById(inputID);
            System.out.println("Event has been deleted: " + event.get().getPublicEventName());
        }
    }

    // 4- CHANGING EVENT FROM DATABASE BY ID AND SAVING AFTER
    public void changeEventInDB() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        showEventsInDB();
        System.out.println("Input ID for changing event: ");
        Integer inputID = Integer.parseInt(input.readLine());
        if (eventRepository.findById(inputID).isPresent()) {
            Optional<PublicEvent> eventForEdit = eventRepository.findById(inputID);
            PublicEvent event = converterOptionalToPublicEvent(eventForEdit);
            List<Ticket> eventTicketList = event.getTickets();
            Boolean changeProcess = true;
            while (changeProcess) {
                System.out.println(event);
                System.out.println("\nВыберте что нужно изменить: 1 - Имя события, 2 - Дату, 3 - Время, 4 -  RARS, 5 - Номер зала ,  6 - Кол-во билетов , 0 - завершить редактирование");
                switch (Integer.parseInt(input.readLine())) {
                    case (1):
                        System.out.println("Предыдущее значение " + event.getPublicEventName() + " введите новое название события:");
                        event.setPublicEventName(input.readLine());
                        System.out.println("Изменение принято");
                        break;
                    case (2):
                        System.out.println("Предыдущее значение " + event.getPublicEventStartDate() + " введите новоую дату события:");
                        event.setPublicEventStartDate(input.readLine());
                        System.out.println("Изменение принято");
                        break;
                    case (3):
                        System.out.println("Предыдущее значение " + event.getPublicEventStartTime() + " введите новое время события:");
                        event.setPublicEventStartTime(input.readLine());
                        System.out.println("Изменение принято");
                        break;
                    case (4):
                        System.out.println("Предыдущее значение " + event.getRatingRARS() + " введите возврастной рейтинг:");
                        event.setRatingRARS(input.readLine());
                        System.out.println("Изменение принято");
                        break;
                    case (5):
                        System.out.println("Предыдущее значение " + event.getNumberHall() + " введите номер зала:");
                        event.setNumberHall(Integer.parseInt(input.readLine()));
                        System.out.println("Изменение принято");
                        break;
                    case (6):
                        //int eventEditedTicketsCountBefore = eventEditedTicketsCount;
                        int eventEditedTicketsCountBefore = event.getTicketsIssued();
                        System.out.println("Предыдущее значение " + eventEditedTicketsCountBefore + " введите новое кол-во билетов:");
                        int eventEditedTicketsCount = Integer.parseInt(input.readLine());
                        //МЕНЯЕМ КОЛИЧЕСТВО БИЛЕТОВ ЧТО ВООБЩЕ НЕ ПРОСТО!!!! ОНИ МОГУТ БЫТЬ ПРОДАНЫЕ УЖЕ, НО МЫ НА ЭТО НЕ БУДЕМ СМОТРЕТЬ В РЯДЕ СЛУЧАЕ НА ЭТО
                        if (eventEditedTicketsCountBefore != eventEditedTicketsCount) { //we have changed number of tickets - > re-generate tickets
                            System.out.println("Amount tickets have been changed.");
                            if (eventEditedTicketsCountBefore < eventEditedTicketsCount) {// билетов стало больше - > добавим разницу на сколько стало больше
                                System.out.println("Amount tickets have been increased!");
                                event.setTicketsIssued(eventEditedTicketsCount);

                                //saving OPTIONAL DATA EVENT TO PUBLIC EVENT!
                                for (int i = eventEditedTicketsCountBefore + 1; i <= eventEditedTicketsCount; i++) {
                                    System.out.println("Adding seat number " + i);

                                    eventTicketList.add(new Ticket(null, event, i, false)); //возможно ошибка
                                }
                            } else {//билетов стало меньше, проданные билеты пропадут, некогда описывать логику возврата если билет был продан и потом его удалили с мероприятия!
                                System.out.println("Amount tickets have been decreased.");
                                //System.out.println("Was " + eventEditedTicketList.size() + " changed to " + eventEditedTicketsCount);

                                for (int i = eventTicketList.size() - 1; i >= eventEditedTicketsCount; i--) {
                                    //удаляем все что больше числа билетов, так как кол-во билетов уменьшилось
                                    eventTicketList.remove(i);
                                    //    System.out.println("Проданные билеты с местами которые удалились пропали, послать данные в дополнительный обработчик для возврата проданных билетов!");
                                }
                                //считаем билеты которые проданы на мероприятие для правки переменной проданных билетов
                                int soldedTicketinList = 0;
                                for (Ticket ticket : eventTicketList) {
                                    if (ticket.isTicketStatus()) {
                                        soldedTicketinList++;
                                        //System.out.println("found sold ticket ++ for inc");
                                    }
                                }
                                //System.out.println(" Продано билетов с учетом удаления с хвоста " + soldedTicketinList);
                                event.setTicketsIssued(eventEditedTicketsCount);
                                event.setSoldTicketsCount(soldedTicketinList);
                                event.setTickets(eventTicketList);
                            }
                        }
                        break;
                    case (0):
                        changeProcess = false;
                        System.out.println("Закончили менять событие.");
                        //input.close();
                        break;
                }
            }
            eventRepository.save(event);
            System.out.println("Event has been changed: " + event);
        }
    }

    //5 Selling process of ticket to event
    @Transactional
    public void sellTicketInDB() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        showEventsInDB();
        System.out.println("Input ID for selling ticket to event: ");
        Integer inputID = Integer.parseInt(input.readLine());
        if (eventRepository.findById(inputID).isPresent()) {
            PublicEvent event = eventRepository.findById(inputID).get();

            System.out.println(event.toString());
            List<Ticket> tickets = event.getTickets();
            // если еще есть билеты всего выпущенного меньше проданных
            if (event.getSoldTicketsCount() < event.getTicketsIssued()) {
                System.out.println("Список билетов:");
                for (Ticket ticket : tickets) {
                    System.out.println(ticket.getSeatNumberTicket());
                    //System.out.println(ticket.toString());
                }
                //Вводим номер билета который хотим купить
                System.out.println("Введите номер места который хотите приобрести: ");
                int purchasingNumber = Integer.parseInt(input.readLine());
                if (purchasingNumber > 0 && purchasingNumber <= event.getTickets().size()) {
                    for (Ticket ticket : event.getTickets()) {
                        //если билет есть с таким местом и он не продан
                        if (ticket.getSeatNumberTicket() == purchasingNumber && !(ticket.isTicketStatus())) {
                            ticket.setTicketStatus(true);
                            event.increaseSoldTicket();
                            System.out.println("Билет успешно продан");
                            break;
                        }
                        if (ticket.getSeatNumberTicket() == purchasingNumber && (ticket.isTicketStatus())) {
                            System.out.println("Билет с номером " + purchasingNumber + " уже продан, продажа не будет выполнена");
                        }
                    }
                } else {
                    System.out.println("Вы ввели некоретное место, продажа не будет выполнена!");
                }
                // System.out.println("Билет на мероприятие " +event.toString() + "\nуспешно продан");//добавляем ++ к числу проданных билетов
            } else {
                System.out.println("Нет свободных билетов");
            }
            //saving
            eventRepository.save(event);
        }
    }

    //6 TICKET RETURN PROCESS
    public void returnTicketInDB() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        showEventsInDB();
        System.out.println("Input ID for return ticket to event: ");
        Integer inputID = Integer.parseInt(input.readLine());
        Ticket testTicket = null;
        if (eventRepository.findById(inputID).isPresent()) {
            Optional<PublicEvent> eventForEdit = eventRepository.findById(inputID);
            //сразу преобразовываем обект в PublicEvent
            PublicEvent event = converterOptionalToPublicEvent(eventForEdit);
            System.out.println("Возврат билета на мероприятие " + event);
            System.out.println("Список билетов");
            for (Ticket ticket : event.getTickets()) {
                System.out.println(ticket.toString());
            }
            System.out.println("Введите номер места с билета которое хотите вернуть ");
            int seatForReturn = Integer.parseInt(input.readLine());
            for (Ticket ticket : event.getTickets()) {
                if (ticket.getSeatNumberTicket() == seatForReturn && event.getSoldTicketsCount() > 0 && ticket.isTicketStatus()) {
                    //мы в нужном билете нужно проверить что его можно вернуть
                    ticket.setTicketStatus(false);
                    event.decreaseSoldTicket();
                    System.out.println("Билет успешно возращен");
                    testTicket = ticket;
                    break;
                } else {
                    if (event.getSoldTicketsCount() == 0) {
                        System.out.println("Нет купленных билетов");
                    }
                }
            }
            if (testTicket == null) {
                System.out.println("Невозможно вернуть билет с таким местом, он не продан");
            }
            eventRepository.save(event);
        }
    }

    public PublicEvent converterOptionalToPublicEvent(Optional<PublicEvent> inputEvent) {
        PublicEvent event = new PublicEvent();
        event.setId(inputEvent.get().getId());                                            //ID
        event.setPublicEventName(inputEvent.get().getPublicEventName());                  //name
        event.setPublicEventStartDate(inputEvent.get().getPublicEventStartDate());        //start date
        event.setPublicEventStartTime(inputEvent.get().getPublicEventStartTime());        //start time
        event.setRatingRARS(inputEvent.get().getRatingRARS());                            //rars
        event.setNumberHall(inputEvent.get().getNumberHall());                            //hall number
        event.setTicketsIssued(inputEvent.get().getTicketsIssued());                      //ticket issued
        event.setSoldTicketsCount(inputEvent.get().getSoldTicketsCount());                //tickets sold
        event.setTickets(inputEvent.get().getTickets());                                  //ticket list
        return event;
    }
}