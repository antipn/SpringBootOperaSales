package ru.learnup.java2.antipn.spring.boot.operasales;

/**
 * Created with IntelliJ IDEA.
 * User: antipn
 * Created on 09.02.2022, 21:12
 * Description:
 */

public class PublicEvent {
    // создать мероприятие, изменить, удалить
    private String publicEventName; // Название
    private String publicEventStartDate; //Дата начала dd/mm/yyyy
    private String publicEventStartTime; //Время начала  hh:mm
    private String ratingRARS; //Russian Age Rating System +0 +6 +12 +16 +18
    private int ticketsCount; // билетов всего
    private int soldTicketsCount;// всего продано билетов


    public int getSoldTicketsCount() {
        return soldTicketsCount;
    }

    public PublicEvent(String publicEventName, String publicEventStartDate, String publicEventStartTime, String ratingRARS, int ticketsCount) {
        this.publicEventName = publicEventName;
        this.publicEventStartDate = publicEventStartDate;
        this.publicEventStartTime = publicEventStartTime;
        this.ratingRARS = ratingRARS;
        this.ticketsCount = ticketsCount;
    }

    public String getPublicEventName() {
        return publicEventName;
    }

    @Override
    public String toString() {
        return "Название: " + publicEventName + "\nАттрибуты: " + publicEventStartDate + "\t" + publicEventStartTime + "\t" + ratingRARS + "\t Выпущено " + ticketsCount + "\t Продано " + soldTicketsCount;
    }

    public String getPublicEventStartDate() {
        return publicEventStartDate;
    }

    public String getPublicEventStartTime() {
        return publicEventStartTime;
    }

    public String getRatingRARS() {
        return ratingRARS;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setPublicEventName(String publicEventName) {
        this.publicEventName = publicEventName;
    }

    public void setPublicEventStartDate(String publicEventStartDate) {
        this.publicEventStartDate = publicEventStartDate;
    }

    public void setPublicEventStartTime(String publicEventStartTime) {
        this.publicEventStartTime = publicEventStartTime;
    }

    public void setRatingRARS(String ratingRARS) {
        this.ratingRARS = ratingRARS;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public void increaseSoldTicket() {
        this.soldTicketsCount++;
    }

    public void decreaseSoldTicket() {
        this.soldTicketsCount--;
    }
}
