package ru.learnup.java2.antipn.spring.boot.operasales.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learnup.java2.antipn.spring.boot.operasales.dto.EntityNotFoundResponse;
import ru.learnup.java2.antipn.spring.boot.operasales.dto.PublicEventDto;
import ru.learnup.java2.antipn.spring.boot.operasales.dto.TicketDto;
import ru.learnup.java2.antipn.spring.boot.operasales.services.PublicEventManager;
import ru.learnup.java2.antipn.spring.boot.operasales.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/app/v1/events") //наш контроллер начинает с этого пути + остальное методами и их маппингом

public class PublicEventController {

    private final PublicEventManager eventManager;

    @Autowired
    public PublicEventController(PublicEventManager eventManager) {
        this.eventManager = eventManager;
    }

    //Show all events in DB in browser --> 1 task done
    // http://localhost:8080/app/v1/events/view-all
    @GetMapping("/view-all")
    public Collection<PublicEventDto> eventsAll() {
        for (PublicEventDto eventDto : eventManager.findAllEvents()) {
            System.out.println(eventDto); //вывод в консоль
        }
        return eventManager.findAllEvents();
    }

    // http://localhost:8080/app/v1/events/view-1
    @GetMapping("/view-{id}")
    public PublicEventDto getEvent(@PathVariable int id) {
        return eventManager.findEventById(id);
    }

    //Delete event in DB through browser  --> 3 task done
    // http://localhost:8080/app/v1/events/event/delete-1
    @DeleteMapping("/event/delete-{id}")
    public void deleteEvent(@PathVariable int id) {
        System.out.println("Deleting event...");
        eventManager.deleteEventByID(id);
    }

    //Post -> save record  --> 2 task done
    // http://localhost:8080/app/v1/events/event/new
    @PostMapping("/event/new")
    public PublicEventDto addEvent(@RequestBody PublicEventDto eventDto) {
        System.out.println("New event...");
        return eventManager.saveEvent(eventDto);
    }

    //Put --> update record --> 4 task done
    // http://localhost:8080/app/v1/events/event/event/update-5
    @PutMapping("/event/update-{id}")
    public PublicEventDto updateEvent(@RequestBody PublicEventDto eventDto, @PathVariable(name = "id") int id) {
        eventDto.setId(id); //специально делаем так как может прийти eventDto с ID
        System.out.println("Updating record...");
        return eventManager.updateEvent(eventDto);
    }

    //просмотр билетов на событие вернее посадочных мест for 5 task done
    // http://localhost:8080/app/v1/events/view-1/tickets
    @GetMapping("/view-{id}/tickets")
    public List<TicketDto> viewTickets(@PathVariable(name = "id") int id) {
        PublicEventDto eventDto = eventManager.findEventById(id);
        List<TicketDto> ticketsDto = eventDto.getTickets();
        return ticketsDto;
    }

    //продажа билета --> 5 task done
    // http://localhost:8080/app/v1/events/sell-1/1
    @PostMapping("/sell-{event_id}/{ticket_seat}")
    public TicketDto sellTicket(@PathVariable(name = "event_id") int eventId, @PathVariable(name = "ticket_seat") int ticketSeat) {
        System.out.println("Purchasing process...");
        return eventManager.sellTicket(eventId, ticketSeat);
    }

    //продажа билета первого свободного из доступных, чтобы не вводить номер места --> 5.1 task done
    // http://localhost:8080/app/v1/events/sell-1/available
    @PostMapping("/sell-{event_id}/available")
    public TicketDto sellTicket(@PathVariable(name = "event_id") int eventId) {
        System.out.println("Purchasing process first available ticket to event.");
        return eventManager.sellTicket(eventId);
    }

    //возврат билета 6 task done
    // http://localhost:8080/app/v1/events/return/ticket-8
    @PutMapping("/return/ticket-{id}")
    public TicketDto returnTicket(@PathVariable(name = "id") int id) {
        System.out.println("Return the ticket...");
        return eventManager.updateTicket(id);
    }

    @ExceptionHandler
    public ResponseEntity<EntityNotFoundResponse> handleException(EntityNotFoundException ex) {
        EntityNotFoundResponse response = new EntityNotFoundResponse();
        response.setEntityName(ex.getEntityName());
        response.setEntityId(ex.getEntityId());
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}