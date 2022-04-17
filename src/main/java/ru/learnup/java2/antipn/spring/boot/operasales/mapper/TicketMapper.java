package ru.learnup.java2.antipn.spring.boot.operasales.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.learnup.java2.antipn.spring.boot.operasales.dto.TicketDto;
import ru.learnup.java2.antipn.spring.boot.operasales.entity.Ticket;

import java.util.List;

@Mapper(uses = {PublicEventMapper.class})

public interface TicketMapper {

    TicketMapper MAPPER = Mappers.getMapper(TicketMapper.class);

    TicketDto toDto(Ticket ticket);

    List<TicketDto> toDtoList(List<Ticket> tickets);

    @InheritInverseConfiguration
    Ticket toEntity(TicketDto ticketDto);

    List<Ticket> toEntityList(List<TicketDto> ticketsDto);
}