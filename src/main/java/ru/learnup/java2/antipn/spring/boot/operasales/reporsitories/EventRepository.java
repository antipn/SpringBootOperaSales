package ru.learnup.java2.antipn.spring.boot.operasales.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.PublicEvent;

import java.util.List;


public interface EventRepository extends JpaRepository<PublicEvent, Integer> {
    List<PublicEvent> getAllByPublicEventNameContains(String nameContains); //по имени нативный запрос Spring

    List<PublicEvent> getAllByRatingRARSContains(String rarsContains); //по рейтингу

}
