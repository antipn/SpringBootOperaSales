package ru.learnup.java2.antipn.spring.boot.operasales.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tickets_seq")
    @SequenceGenerator(name = "tickets_seq", sequenceName = "hibernate_sequence_tickets", allocationSize = 1)
    @Column(name = "id")
    private Integer id; // номер билета

    @JoinColumn(name = "event_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PublicEvent event;  //номер события для этого билета

    @Column(name = "seat_number")
    private Integer seatNumberTicket; // номер места билета

    @Column(name = "ticket_status") //продано не продано
    private boolean ticketStatus;

    @Override
    public String toString() {
        return "Номер билета = " + id + " , Номер места = " + seatNumberTicket + " , Билет доступен: " + (!ticketStatus ? "Да" : "Нет");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PublicEvent getEvent() {
        return event;
    }

    public void setEvent(PublicEvent event) {
        this.event = event;
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
