package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.PermissionChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class InscriptionServiceTest {

    private String validCard = "6666777788882222";
    private String invalidCard = "666677778888222A";

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionDAO inscriptionDao;

    @Autowired
    private SportEventDAO sportEventDao;

    @Autowired
    private SportEventTypeDAO sportEventTypeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProvinceDAO provinceDao;

    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com",
                User.RoleType.PARTICIPANT);
    }

    private SportEventType createEventType() {
        return sportEventTypeDao.save(new SportEventType("Natación"));
    }

    private SportEventType createEventType1() {
        return sportEventTypeDao.save(new SportEventType("Running"));
    }

    private Province createProvince() {
        Province province1 = new Province("province1");
        return provinceDao.save(province1);
    }

    private Province createProvince1() {
        Province province2 = new Province("province2");
        return provinceDao.save(province2);
    }

    private SportEvent createEvent(LocalDateTime date) {
        return new SportEvent("LakeTravel", "description", date, BigDecimal.valueOf(100.00), "A Coruña", 3000,
                createEventType(), createProvince());
    }

    private SportEvent createEvent1() {
        return sportEventDao.save(new SportEvent("Maraton", "description", LocalDateTime.of(2024, 7, 25, 0, 0, 0),
                BigDecimal.valueOf(100.00), "A Coruña", 3000, createEventType1(), createProvince1()));
    }

    @Test
    public void testInscribe() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException {

        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        User user = createUser("Roberto");
        userDao.save(user);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        Inscription inscriptionBD = inscriptionDao.findById(inscription.getId()).get();

        assertEquals(inscription, inscriptionBD);
        assertEquals(sportEvent, inscriptionBD.getEvent());
        assertEquals(validCard, inscriptionBD.getCreditCard());
        assertEquals(1, inscription.getDorsal());
        assertFalse(inscription.isPicked());
        assertFalse(inscription.isRated());
        assertTrue(sportEvent.getParticipants() != 0);

    }

    @Test
    public void testInvalidEventInscribe() {
        User user = createUser("Roberto");
        userDao.save(user);
        assertThrows(InstanceNotFoundException.class,
                () -> inscriptionService.inscribe(user.getId(), NON_EXISTENT_ID, validCard));
    }

    @Test
    public void testInvalidUserInscribe() {
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        assertThrows(InstanceNotFoundException.class,
                () -> inscriptionService.inscribe(NON_EXISTENT_ID, sportEvent.getId(), validCard));
    }

    @Test
    public void testInscriptionPeriodFinished() {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.now());
        sportEventDao.save(sportEvent);
        assertThrows(InscriptionTimeFinishedException.class,
                () -> inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard));
    }

    @Test
    public void testSportEventFullInscribe() {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        sportEvent.setParticipants(sportEvent.getSpots());
        sportEventDao.save(sportEvent);
        assertThrows(SportEventFullException.class,
                () -> inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard));
    }

    @Test
    public void testUserAlreadyInscribedInscribe() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        assertThrows(InscriptionExistException.class,
                () -> inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard));

    }

    @Test
    public void testSeeInscriptionHistory() throws InstanceNotFoundException, InscriptionTimeFinishedException,
            SportEventFullException, InscriptionExistException {
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        SportEvent sportEvent1 = createEvent1();
        sportEventDao.save(sportEvent1);
        User user = createUser("Roberto");
        userDao.save(user);

        Inscription inscription1 = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        Inscription inscription2 = inscriptionService.inscribe(user.getId(), sportEvent1.getId(), validCard);

        Block<Inscription> expectedBlock = new Block<>(Arrays.asList(inscription1, inscription2), false);
        assertEquals(expectedBlock, inscriptionService.seeInscriptionHistory(user.getId(), 0, 4));

    }

    @Test
    public void testGiveDorsalOfNonExistentInscription() {
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        assertThrows(InstanceNotFoundException.class,
                () -> inscriptionService.giveDorsal(sportEvent.getId(), 123456L, validCard));
    }

    @Test
    public void testNumbersAlreadyGivenException() throws InstanceNotFoundException,
            InscriptionTimeFinishedException, SportEventFullException, InscriptionExistException,
            SportEventAlreadyStartedException, DorsalAlreadyGivenException, WrongInscriptionException, CardDoesntMatchException {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        inscriptionService.giveDorsal(sportEvent.getId(), inscription.getId(), validCard);

        assertThrows(DorsalAlreadyGivenException.class,
                () -> inscriptionService.giveDorsal(sportEvent.getId(), inscription.getId(),
                        inscription.getCreditCard()));
    }

    @Test
    public void testGiveNumbersOfAlreadyStartedSession() throws InstanceNotFoundException,
            InscriptionTimeFinishedException, SportEventFullException, InscriptionExistException {
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        User user = createUser("Roberto");
        userDao.save(user);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        sportEvent.setDate(LocalDateTime.now().minusHours(1));
        sportEventDao.save(sportEvent);
        assertThrows(SportEventAlreadyStartedException.class,
                () -> inscriptionService.giveDorsal(sportEvent.getId(), inscription.getId(),
                        inscription.getCreditCard()));
    }

    @Test
    public void giveDorsalTest() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException, SportEventAlreadyStartedException,
            DorsalAlreadyGivenException, WrongInscriptionException, CardDoesntMatchException {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        inscriptionService.giveDorsal(sportEvent.getId(), inscription.getId(), validCard);
        assertEquals(true, inscription.isPicked());

    }

    @Test
    public void InvalidEventGiveDorsalTest() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException, SportEventAlreadyStartedException,
            DorsalAlreadyGivenException, WrongInscriptionException {
        User user = createUser("Roberto");
        userDao.save(user);
        SportEvent sportEvent = createEvent(LocalDateTime.of(2024, 12, 12, 12, 12));
        sportEventDao.save(sportEvent);
        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);
        SportEvent sportEvent1 = createEvent1();
        sportEvent1.setDate(LocalDateTime.of(2024, 11, 12, 12, 12));
        sportEventDao.save(sportEvent1);
        assertThrows(WrongInscriptionException.class,
                () -> inscriptionService.giveDorsal(sportEvent1.getId(), inscription.getId(), validCard));
    }

    @Test
    public void testEventRateAndEventValorationVerification() throws InscriptionTimeFinishedException,
            InstanceNotFoundException, SportEventFullException, InscriptionExistException, EventAlreadyRatedException,
            EventNotStartedException, RatePeriodFinishedException, PermissionException {
        User user = createUser("Mario");
        userDao.save(user);
        SportEvent sportEvent = createEvent1();
        sportEventDao.save(sportEvent);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        sportEvent.setDate(LocalDateTime.now().minusHours(1));
        sportEventDao.save(sportEvent);

        inscriptionService.eventRate(user.getId(), inscription.getId(), 4);

        Optional<Inscription> bddInscription = inscriptionDao.findById(inscription.getId());
        Optional<SportEvent> bddSportEvent = sportEventDao.findById(sportEvent.getId());

        assertTrue(bddInscription.isPresent());
        assertEquals(4, bddInscription.get().getValoration());
        assertTrue(bddSportEvent.isPresent());
        assertEquals(4, bddSportEvent.get().getSumValoraciones());
        assertEquals(1, bddSportEvent.get().getValoraciones());
    }

    @Test
    public void testInvalidInscriptionRate() {
        User user = createUser("Roberto");
        assertThrows(InstanceNotFoundException.class, () -> inscriptionService.eventRate(user.getId(), (long) -1, 3));
    }

    @Test
    public void testEventAlreadyRated() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException, EventAlreadyRatedException, EventNotStartedException,
            RatePeriodFinishedException, PermissionException {
        User user = createUser("Mario");
        userDao.save(user);
        SportEvent sportEvent = createEvent1();
        sportEventDao.save(sportEvent);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        sportEvent.setDate(LocalDateTime.now().minusHours(1));
        sportEventDao.save(sportEvent);

        inscriptionService.eventRate(user.getId(), inscription.getId(), 4);

        assertThrows(EventAlreadyRatedException.class,
                () -> inscriptionService.eventRate(user.getId(), inscription.getId(), 4));
    }

    @Test
    public void testNotStartedEventRate() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException {
        User user = createUser("Mario");
        userDao.save(user);
        SportEvent sportEvent = createEvent1();
        sportEventDao.save(sportEvent);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        assertThrows(EventNotStartedException.class,
                () -> inscriptionService.eventRate(user.getId(), inscription.getId(), 4));
    }

    @Test
    public void testRatePeriodFinished() throws InscriptionTimeFinishedException, InstanceNotFoundException,
            SportEventFullException, InscriptionExistException {
        User user = createUser("Mario");
        userDao.save(user);
        SportEvent sportEvent = createEvent1();
        sportEventDao.save(sportEvent);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        sportEvent.setDate(LocalDateTime.now().minusDays(16));
        sportEventDao.save(sportEvent);

        assertThrows(RatePeriodFinishedException.class,
                () -> inscriptionService.eventRate(user.getId(), inscription.getId(), 4));
    }

    @Test
    public void testEventInvalidUserRate() throws InscriptionTimeFinishedException,
            InstanceNotFoundException, SportEventFullException, InscriptionExistException {
        User user = createUser("Mario");
        userDao.save(user);
        SportEvent sportEvent = createEvent1();
        sportEventDao.save(sportEvent);

        Inscription inscription = inscriptionService.inscribe(user.getId(), sportEvent.getId(), validCard);

        sportEvent.setDate(LocalDateTime.now());
        sportEventDao.save(sportEvent);

        User user1 = createUser("Diego");
        userDao.save(user1);

        assertThrows(PermissionException.class,
                () -> inscriptionService.eventRate(user1.getId(), inscription.getId(), 5));
    }

}
