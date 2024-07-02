package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.SportEvent;

import java.util.List;
import java.util.stream.Collectors;

public class SportEventConversor {

    private SportEventConversor() {
    }

    public final static SportEventDto toSportEventDto(SportEvent sportEvent) {

        return new SportEventDto(sportEvent.getSportEventName(), sportEvent.getId(), sportEvent.getDescription(),
                sportEvent.getDate(),
                sportEvent.getPrice(), sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getSpots(),
                sportEvent.getSumValoraciones(),
                sportEvent.getValoraciones(), sportEvent.getType().getId(), sportEvent.getProvince().getId(),
                sportEvent.hasStarted());

    }

    public final static List<SportEventSummaryDto> toSportEventSummaryDtos(List<SportEvent> sportEvents) {
        return sportEvents.stream().map(p -> toSportEventSummaryDto(p)).collect(Collectors.toList());
    }

    private final static SportEventSummaryDto toSportEventSummaryDto(SportEvent sportEvent) {
        return new SportEventSummaryDto(sportEvent.getSportEventName(), sportEvent.getId(), sportEvent.getDate(),
                sportEvent.getType().getId(), sportEvent.getProvince().getId(), sportEvent.getSumValoraciones(),
                sportEvent.getValoraciones(), sportEvent.hasStarted());
    }

}
