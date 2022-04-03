package ru.learnup.java2.antipn.spring.boot.operasales.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.Ticket;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> getTicketsByTicketStatus(boolean statusTicket); //по статусу билета продан не продан
}
