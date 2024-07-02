package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;

public interface InscriptionService {

        Inscription inscribe(Long userId, Long sportEventId, String creditCard) throws InstanceNotFoundException,
                        InscriptionTimeFinishedException, InscriptionExistException, SportEventFullException;

        Block<Inscription> seeInscriptionHistory(Long userId, int page, int size);

        int giveDorsal(Long eventId, Long inscriptionId, String creditCardNumber) throws InstanceNotFoundException,
                        DorsalAlreadyGivenException, SportEventAlreadyStartedException, WrongInscriptionException, CardDoesntMatchException;

        void eventRate(Long userid, Long inscriptionId, int rate)
                        throws InstanceNotFoundException, RatePeriodFinishedException,
                        EventAlreadyRatedException, EventNotStartedException, PermissionException;

}
