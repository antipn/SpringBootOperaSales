package ru.learnup.java2.antipn.spring.boot.operasales.dto;

import lombok.*;

import java.util.List;

@Data
public class PublicEventDto {
    private int id;
    private String publicEventName;
    private String publicEventStartDate;
    private String publicEventStartTime;
    private int numberHall;
    private String ratingRARS;
    private int ticketsIssued;
    private int soldTicketsCount;
    private List<TicketDto> tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicEventName() {
        return publicEventName;
    }

    public void setPublicEventName(String publicEventName) {
        this.publicEventName = publicEventName;
    }

    public String getPublicEventStartDate() {
        return publicEventStartDate;
    }

    public void setPublicEventStartDate(String publicEventStartDate) {
        this.publicEventStartDate = publicEventStartDate;
    }

    public String getPublicEventStartTime() {
        return publicEventStartTime;
    }

    public void setPublicEventStartTime(String publicEventStartTime) {
        this.publicEventStartTime = publicEventStartTime;
    }

    public int getNumberHall() {
        return numberHall;
    }

    public void setNumberHall(int numberHall) {
        this.numberHall = numberHall;
    }

    public String getRatingRARS() {
        return ratingRARS;
    }

    public void setRatingRARS(String ratingRARS) {
        this.ratingRARS = ratingRARS;
    }

    public int getTicketsIssued() {
        return ticketsIssued;
    }

    public void setTicketsIssued(int ticketsIssued) {
        this.ticketsIssued = ticketsIssued;
    }

    public int getSoldTicketsCount() {
        return soldTicketsCount;
    }

    public void setSoldTicketsCount(int soldTicketsCount) {
        this.soldTicketsCount = soldTicketsCount;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
