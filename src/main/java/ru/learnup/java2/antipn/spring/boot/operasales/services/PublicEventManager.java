package ru.learnup.java2.antipn.spring.boot.operasales.services;

import ru.learnup.java2.antipn.spring.boot.operasales.entity.PublicEvent;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.Ticket;

import java.io.IOException;
import java.util.Collection;

public interface PublicEventManager {


    //  repos level
    public Collection<PublicEvent> getAllEvents();

    public Boolean deleteEventByIdINDB(int id);

    public void addEvent() throws IOException;

    public void addEventQuick();

    public void showEventsInDB();

    public void deleteEventInDB() throws IOException;

    public void changeEventInDB() throws IOException;

    public void sellTicketInDB() throws IOException;

    public void returnTicketInDB() throws IOException;

    public Collection<Ticket> getAllTickets();

    public static PublicEvent createEvent() {
        return null;
    }
}
