package ru.learnup.java2.antipn.spring.boot.operasales.dto;

import lombok.*;

@Data
public class TicketDto {

    private Integer id;
//    private PublicEventDto event;
    private Integer seatNumberTicket;
    private boolean ticketStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getSeatNumberTicket() {
        return seatNumberTicket;
    }

    public void setSeatNumberTicket(Integer seatNumberTicket) {
        this.seatNumberTicket = seatNumberTicket;
    }

    public boolean isTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}


