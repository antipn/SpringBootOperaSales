package ru.learnup.java2.antipn.spring.boot.operasales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.learnup.java2.antipn.spring.boot.operasales.dto.TicketDto;
import ru.learnup.java2.antipn.spring.boot.operasales.services.PublicEventManager;

import java.util.Collection;

@RestController
@RequestMapping("/app/v1/tickets") //наше контроллер начинает с этого пути + остальное методами и их маппингом

public class TicketController {
    private final PublicEventManager eventManager;

    @Autowired
    public TicketController(PublicEventManager eventManager) {
        this.eventManager = eventManager;
    }


    //all tickets DTO
    @GetMapping
    public Collection<TicketDto> tickets() {
        return eventManager.findAllTickets();
    }

    //get one ticket by id
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable int id) {
        return eventManager.findTicketById(id);
    }

}
