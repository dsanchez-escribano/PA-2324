package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.entities.SportEvent;
import es.udc.paproject.backend.model.entities.SportEventType;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.SportEventService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.common.JwtGenerator;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static es.udc.paproject.backend.rest.dtos.ProvinceConversor.toProvinceDtos;
import static es.udc.paproject.backend.rest.dtos.SportEventConversor.*;
import static es.udc.paproject.backend.rest.dtos.SportEventTypeConversor.toSportEventTypeDtos;

@RestController
@RequestMapping("/sportevents")
public class SportEventController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private SportEventService sportEventService;

    @GetMapping("/types")
    public List<SportEventTypeDto> findAllSportEventTypes() {
        return toSportEventTypeDtos(sportEventService.findAllSportEventTypes());
    }

    @GetMapping("/provinces")
    public List<ProvinceDto> findAllProvinces() {
        return toProvinceDtos(sportEventService.findAllProvinces());
    }

    @GetMapping("/events/{id}")
    public SportEventDto findEventById(@PathVariable Long id) throws InstanceNotFoundException {
        return toSportEventDto(sportEventService.findEventById(id));
    }

    @GetMapping("/events")
    public BlockDto<SportEventSummaryDto> findEvents(
            @RequestParam(required = false) Long provinceId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page) {

        Block<SportEvent> sportEventBlock = sportEventService.search(provinceId, typeId, startDate, endDate, page, 2);

        return new BlockDto<>(toSportEventSummaryDtos(sportEventBlock.getItems()), sportEventBlock.getExistMoreItems());

    }
}
