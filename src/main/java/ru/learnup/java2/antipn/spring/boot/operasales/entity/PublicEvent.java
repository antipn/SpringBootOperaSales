package ru.learnup.java2.antipn.spring.boot.operasales.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class PublicEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_seq")
    @SequenceGenerator(name = "events_seq",
            sequenceName = "hibernate_sequence_event", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String publicEventName; // Название

    @Column(name = "date")
    private String publicEventStartDate; //Дата начала dd/mm/yyyy

    @Column(name = "time")
    private String publicEventStartTime; //Время начала  hh:mm

    @Column(name = "hall")
    private Integer numberHall; // the number of Hall where will be event

    @Column(name = "rars")
    private String ratingRARS; //Russian Age Rating System +0 +6 +12 +16 +18

    @Column(name = "tickets_issued")
    private int ticketsIssued; // билетов всего

    @Column(name = "tickets_sold")
    private int soldTicketsCount;// всего продано билетов

    //к сожалению на данном этапе разработки LAZY не работает, приходится пользоваться EAGER, надеюсь после прикручивания транзакций получится это обойти!
    //однозначного ответа честно не нашел
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets; // list of ticket for event


    public PublicEvent(Integer id, String publicEventName, String publicEventStartDate, String publicEventStartTime, int numberHall, String ratingRARS, int ticketsCount) {
        this.id = id;
        this.publicEventName = publicEventName;
        this.publicEventStartDate = publicEventStartDate;
        this.publicEventStartTime = publicEventStartTime;
        this.ratingRARS = ratingRARS;
        this.numberHall = numberHall;
        this.ticketsIssued = ticketsCount;
    }

    public void increaseSoldTicket() {
        this.soldTicketsCount++;

    }

    public void decreaseSoldTicket() {
        this.soldTicketsCount--;
    }

    @Override
    public String toString() {
        return "ID  " + (id == null ? "" : id) + "  название: " + publicEventName + "\nАттрибуты: " + publicEventStartDate + "\t" + publicEventStartTime + "\t" + ratingRARS + "\t Номер зала " + numberHall + "\t Выпущено " + ticketsIssued + "\t Билетов продано " + soldTicketsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getNumberHall() {
        return numberHall;
    }

    public void setNumberHall(Integer numberHall) {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
