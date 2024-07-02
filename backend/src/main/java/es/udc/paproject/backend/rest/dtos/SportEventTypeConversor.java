package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.SportEvent;
import es.udc.paproject.backend.model.entities.SportEventType;

import java.util.List;
import java.util.stream.Collectors;

public class SportEventTypeConversor {

    private SportEventTypeConversor() {}

    public final static SportEventTypeDto toSportEventTypeDto(SportEventType sportEventType) {
        return new SportEventTypeDto(sportEventType.getTypeName(), sportEventType.getId());
    }

    public final static List<SportEventTypeDto> toSportEventTypeDtos(List<SportEventType> sportEventTypes) {
        return sportEventTypes.stream().map(s -> toSportEventTypeDto(s)).collect(Collectors.toList());
    }

}
