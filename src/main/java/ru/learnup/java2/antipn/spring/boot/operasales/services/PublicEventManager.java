package ru.learnup.java2.antipn.spring.boot.operasales.services;

import ru.learnup.java2.antipn.spring.boot.operasales.dto.PublicEventDto;
import ru.learnup.java2.antipn.spring.boot.operasales.dto.TicketDto;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.PublicEvent;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.Ticket;

import java.io.IOException;
import java.util.Collection;

public interface PublicEventManager {

    //  contollers level

    public Collection<TicketDto> findAllTickets();

    public Collection<PublicEventDto> findAllEvents();

    public void deleteEventByID(int id);

    public TicketDto findTicketById(int id);

    public PublicEventDto findEventById(int id);

    public PublicEventDto saveEvent(PublicEventDto eventDto);

    public PublicEventDto updateEvent (PublicEventDto eventDto);

    public TicketDto sellTicket(int eventId, int ticketSeat);

    public TicketDto sellTicket(int eventId);

    public TicketDto updateTicket(int id);

    Boolean existEventByID(int id);

    Boolean existTicketByID(int id);

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
