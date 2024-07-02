package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringReader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private InscriptionDAO inscriptionDao;

    @Autowired
    private SportEventDAO sportEventDao;

    @Override
    public Inscription inscribe(Long userId, Long sportEventId, String creditCard)
            throws InstanceNotFoundException, InscriptionTimeFinishedException,
            InscriptionExistException, SportEventFullException {

        User user = permissionChecker.checkUser(userId);
        Optional<SportEvent> opSportEvent = sportEventDao.findById(sportEventId);

        if (!opSportEvent.isPresent()) {
            throw new InstanceNotFoundException("project.entities.sportEvent", sportEventId);
        }

        SportEvent sportEvent = opSportEvent.get();

        if (Duration.between(LocalDateTime.now(), sportEvent.getDate()).toHours() < 24) {
            throw new InscriptionTimeFinishedException();
        }

        if (permissionChecker.checkInscription(sportEventId, userId)) {
            throw new InscriptionExistException();
        }

        if (sportEvent.getSpots() == sportEvent.getParticipants()) {
            throw new SportEventFullException();
        }

        sportEvent.setParticipants(sportEvent.getParticipants() + 1);

        Inscription inscription = new Inscription(creditCard, sportEvent, user, sportEvent.getParticipants());
        inscriptionDao.save(inscription);

        return inscription;
    }

    @Override
    @Transactional(readOnly = true)

    public Block<Inscription> seeInscriptionHistory(Long userId, int page, int size) {
        Slice<Inscription> slice = inscriptionDao.findByUserIdOrderByInscriptionDateDesc(userId,
                PageRequest.of(page, size));
        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    public int giveDorsal(Long eventId, Long inscriptionId, String creditCardNumber) throws InstanceNotFoundException,
            DorsalAlreadyGivenException, SportEventAlreadyStartedException,
            WrongInscriptionException, CardDoesntMatchException {

        Optional opEvent = sportEventDao.findById(eventId);

        if (!opEvent.isPresent()) {
            throw new InstanceNotFoundException("project.entities.sportEvent", eventId);
        }

        SportEvent event = sportEventDao.findById(eventId).get();

        if (inscriptionDao.findById(inscriptionId).isEmpty()) {
            throw new InstanceNotFoundException("project.entities.inscription", inscriptionId);
        }

        Inscription inscription = inscriptionDao.findById(inscriptionId).get();

        if (!event.equals(inscription.getEvent())) {
            throw new WrongInscriptionException("Event and Inscription doesn't match");
        }

        if (event.hasStarted()) {
            throw new SportEventAlreadyStartedException("Session with ID " + event.getId() + " has already started");
        }

        if (inscription.isPicked()) {
            throw new DorsalAlreadyGivenException(
                    "Dorsal of purchase with id " + inscriptionId + " were already given");
        }


        if (!inscription.getCreditCard().equals(creditCardNumber)) {
            throw new CardDoesntMatchException("Card " + creditCardNumber + " doesnt macth");
        }

        inscription.setPicked(true);
        inscriptionDao.save(inscription);
        return inscription.getDorsal();
    }

    @Override
    public void eventRate(Long userId, Long inscriptionId, int rate)
            throws InstanceNotFoundException, RatePeriodFinishedException,
            EventAlreadyRatedException, EventNotStartedException, PermissionException {
        Optional<Inscription> optionalInscription = inscriptionDao.findById(inscriptionId);

        if (!optionalInscription.isPresent()) {
            throw new InstanceNotFoundException("project.entities.inscription", inscriptionId);
        }

        Inscription inscription = optionalInscription.get();

        User user = permissionChecker.checkUser(userId);

        if (!user.equals(inscription.getUser())) {
            throw new PermissionException();
        }

        SportEvent sportEvent = inscription.getEvent();

        if (inscription.isRated()) {
            throw new EventAlreadyRatedException();
        }

        if (LocalDateTime.now().isBefore(sportEvent.getDate())) {
            throw new EventNotStartedException();
        }

        if (Duration.between(sportEvent.getDate(), LocalDateTime.now()).toDays() > 15) {
            throw new RatePeriodFinishedException();
        }

        inscription.setValoration(rate);
        inscription.setRated(true);
        inscriptionDao.save(inscription);
        sportEvent.setSumValoraciones(rate + sportEvent.getSumValoraciones());
        sportEvent.setValoraciones(sportEvent.getValoraciones() + 1);
        sportEventDao.save(sportEvent);
    }
}
