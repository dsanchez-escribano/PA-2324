package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.common.JwtGenerator;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.InscriptionConversor;
import es.udc.paproject.backend.rest.dtos.InscriptionDto;
import es.udc.paproject.backend.rest.dtos.InscriptionSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {

        private final static String EVENT_ALREADY_RATED = "project.exceptions.EventAlreadyRatedException";
        private final static String EVENT_NOT_STARTED = "project.exceptions.EventNotStartedException";
        private final static String RATE_PERIOD_FINISHED = "project.exceptions.RatePeriodFinishedException";
        private final static String EVENT_ALREADY_STARTED = "project.exceptions.SportEventAlreadyStartedException";
        private final static String EVENT_FULL = "project.exceptions.SportEventFullException";
        private final static String INSCRIPTION_TIME_FINISHED = "project.exceptions.InscriptionTimeFinishedException";
        private final static String INSCRIPTION_EXIST = "project.exceptions.InscriptionExistException";
        private final static String INVALID_CARD_NUMBER = "project.exceptions.InvalidCreditCardNumberException";
        private final static String DORSAL_ALREADY_GIVEN = "project.exceptions.DorsalAlreadyGivenException";
        private final static String WRONG_INSCRIPTION = "project.exceptions.WrongInscriptionException";
        private final static String CARD_DOESNT_MATCH = "project.exceptions.CardDoesntMatch";
        @Autowired
        private MessageSource messageSource;

        @Autowired
        private JwtGenerator jwtGenerator;

        @Autowired
        private InscriptionService inscriptionService;

        @GetMapping("/inscriptions")
        public BlockDto<InscriptionDto> inscriptionList(@RequestAttribute Long userId,
                        @RequestParam(defaultValue = "0") int page) {
                Block<Inscription> orderBlock = inscriptionService.seeInscriptionHistory(userId, page, 2);
                return new BlockDto<>(InscriptionConversor.toInscriptionDtos(orderBlock.getItems()),
                                orderBlock.getExistMoreItems());
        }

        @ExceptionHandler(DorsalAlreadyGivenException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleDorsalAlreadyGivenException(DorsalAlreadyGivenException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(DORSAL_ALREADY_GIVEN, null,
                                DORSAL_ALREADY_GIVEN, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(WrongInscriptionException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleWrongInscriptionException(WrongInscriptionException exception,
                        Locale locale) {
                String errorMessage = messageSource.getMessage(WRONG_INSCRIPTION, null,
                                WRONG_INSCRIPTION, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(CardDoesntMatchException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleCardDoesntMatchException(CardDoesntMatchException exception,
                        Locale locale) {
                String errorMessage = messageSource.getMessage(CARD_DOESNT_MATCH, null,
                                CARD_DOESNT_MATCH, locale);
                return new ErrorsDto(errorMessage);
        }

        @PostMapping("/giveDorsal")
        public int giveDorsal(@Validated @RequestBody InscriptionSummaryDto params)
                        throws SportEventAlreadyStartedException, DorsalAlreadyGivenException,
                        InstanceNotFoundException,
                        WrongInscriptionException, CardDoesntMatchException {
                return inscriptionService.giveDorsal(params.getEventId(), params.getId(), params.getCreditCard());
        }

        @ExceptionHandler(SportEventFullException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleSportEventFullException(SportEventFullException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(EVENT_FULL, null,
                                EVENT_FULL, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(SportEventAlreadyStartedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleSportEventAlreadyStartedException(SportEventAlreadyStartedException exception,
                        Locale locale) {
                String errorMessage = messageSource.getMessage(EVENT_ALREADY_STARTED, null,
                                EVENT_ALREADY_STARTED, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(InscriptionTimeFinishedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleInscriptionTimeFinishedException(InscriptionTimeFinishedException exception,
                        Locale locale) {
                String errorMessage = messageSource.getMessage(INSCRIPTION_TIME_FINISHED, null,
                                INSCRIPTION_TIME_FINISHED, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(InscriptionExistException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleInscriptionExistException(InscriptionExistException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(INSCRIPTION_EXIST, null,
                                INSCRIPTION_EXIST, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(InvalidCreditCardNumberException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleInvalidCreditCardNumberException(InvalidCreditCardNumberException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(INVALID_CARD_NUMBER, null,
                        INVALID_CARD_NUMBER, locale);
                return new ErrorsDto(errorMessage);
        }

        @PostMapping("/inscribe")
        public HashMap<String, Integer> inscribe(@RequestAttribute Long userId,
                        @Validated @RequestBody InscriptionSummaryDto params)
                throws DuplicateInstanceException, SportEventFullException, InstanceNotFoundException,
                SportEventAlreadyStartedException, InscriptionTimeFinishedException, InscriptionExistException, InvalidCreditCardNumberException {
                if(!params.getCreditCard().matches("[0-9]{16}")){
                        throw new InvalidCreditCardNumberException();
                }

                HashMap<String, Integer> map = new HashMap<>();
                Inscription inscription = inscriptionService.inscribe(userId, params.getEventId(),
                                params.getCreditCard());
                map.put("inscriptionId", inscription.getId().intValue());
                map.put("dorsal", inscription.getDorsal());
                return map;
        }

        @ExceptionHandler(RatePeriodFinishedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleRatePeriodFinishedException(RatePeriodFinishedException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(RATE_PERIOD_FINISHED, null,
                                RATE_PERIOD_FINISHED, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(EventNotStartedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleEventNotStartedException(EventNotStartedException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(EVENT_NOT_STARTED, null,
                                EVENT_NOT_STARTED, locale);
                return new ErrorsDto(errorMessage);
        }

        @ExceptionHandler(EventAlreadyRatedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ErrorsDto handleEventAlreadyRatedException(EventAlreadyRatedException exception, Locale locale) {
                String errorMessage = messageSource.getMessage(EVENT_ALREADY_RATED, null,
                                EVENT_ALREADY_RATED, locale);
                return new ErrorsDto(errorMessage);
        }

        @PostMapping("/inscriptions/{id}/rate")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @ResponseBody
        public void rateEvent(@RequestAttribute Long userId, @PathVariable Long id,
                        @Validated @RequestBody InscriptionSummaryDto params) throws InstanceNotFoundException,
                        RatePeriodFinishedException, EventAlreadyRatedException, EventNotStartedException,
                        PermissionException {

                inscriptionService.eventRate(userId, id, params.getValoration());
        }
}
