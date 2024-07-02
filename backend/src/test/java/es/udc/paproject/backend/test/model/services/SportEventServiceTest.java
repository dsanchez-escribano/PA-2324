package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SportEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SportEventServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);

    @Autowired
    private SportEventTypeDAO typeDao;

    @Autowired
    private ProvinceDAO provinceDao;

    @Autowired
    private SportEventService sportEventService;

    @Autowired
    private SportEventDAO eventDao;
    private SportEventType createEventType(String name){
        return new SportEventType(name);
    }

    private SportEvent createEvent(String name, SportEventType type, Province province) {
        return new SportEvent(name, "descripcion", LocalDateTime.of(2024,12,12,12,12), BigDecimal.valueOf(100.00),"A Coruña", 5000, type, province);
    }


    @Test
    public void testFindAllSportEventTypes() {

        SportEventType type1 = new SportEventType("type1");
        SportEventType type2 = new SportEventType("type2");

        typeDao.save(type1);
        typeDao.save(type2);

        assertEquals(Arrays.asList(type1, type2), sportEventService.findAllSportEventTypes());

    }

    @Test
    public void testFindAllProvinces() {

        Province province1 = new Province("province1");
        Province province2 = new Province("province2");

        provinceDao.save(province1);
        provinceDao.save(province2);

        assertEquals(Arrays.asList(province1, province2), sportEventService.findAllProvinces());

    }

    @Test
    public void testFindEventById() throws InstanceNotFoundException {
        SportEventType type1 = createEventType("natacion");
        typeDao.save(type1);

        Province province1 = new Province("province1");

        provinceDao.save(province1);

        SportEvent event = createEvent("event", type1, province1);
        eventDao.save(event);

        assertEquals(event, sportEventService.findEventById(event.getId()));


    }

    @Test
    public void testFindEventByNonExistentId() {
        assertThrows(InstanceNotFoundException.class, () -> sportEventService.findEventById(NON_EXISTENT_ID));
    }

    @Test
    public void testFindEventByProvince() throws InstanceNotFoundException {
        SportEventType type1 = new SportEventType("type1");
        SportEventType type2 = new SportEventType("type2");

        typeDao.save(type1);
        typeDao.save(type2);

        Province province1 = new Province("province1");
        Province province2 = new Province("province2");
        provinceDao.save(province1);
        provinceDao.save(province2);
        SportEvent event1 = createEvent("event1", type1, province1);
        SportEvent event2 = createEvent("event2", type2, province2);


        eventDao.save(event1);
        eventDao.save(event2);

        Block<SportEvent> expectedBlock = new Block<>(Arrays.asList(event1), false);

        assertEquals(expectedBlock, sportEventService.search(province1.getId(), null, null, null, 0, 2));

    }


    @Test
    public void testFindEventByDates() throws InstanceNotFoundException {
        SportEventType type1 = new SportEventType("type1");
        SportEventType type2 = new SportEventType("type2");

        typeDao.save(type1);
        typeDao.save(type2);

        Province province1 = new Province("province1");
        Province province2 = new Province("province2");

        provinceDao.save(province1);
        provinceDao.save(province2);

        SportEvent event1 = createEvent("event1", type1, province1);
        SportEvent event2 = createEvent("event2", type2, province2);

        eventDao.save(event1);
        eventDao.save(event2);

        Block<SportEvent> expectedBlock = new Block<>(Arrays.asList(event1,event2), false);

        assertEquals(expectedBlock, sportEventService.search(null, null, LocalDate.now(), LocalDate.of(2025,12,12), 0, 2));

    }


    @Test
    public void testFindEventByTypeName() throws InstanceNotFoundException {

        SportEventType type1 = new SportEventType("carrera");
        SportEventType type2 = new SportEventType("natacion");

        typeDao.save(type1);
        typeDao.save(type2);

        Province province1 = new Province("province1");
        Province province2 = new Province("province2");

        provinceDao.save(province1);
        provinceDao.save(province2);

        SportEvent event1 = createEvent("event1", type1, province1);
        SportEvent event2 = createEvent("event2", type2, province2);


        eventDao.save(event1);
        eventDao.save(event2);

        Block<SportEvent> expectedBlock = new Block<>(Arrays.asList(event1), false);

        assertEquals(expectedBlock, sportEventService.search(null, type1.getId(), null, null, 0, 2));

    }

    @Test
    public void testFindEventByAll() throws InstanceNotFoundException {

        Province province1 = new Province("province1");
        Province province2 = new Province("province2");

        provinceDao.save(province1);
        provinceDao.save(province2);

        SportEventType type1 = new SportEventType("carrera");
        SportEventType type2 = new SportEventType("natacion");

        typeDao.save(type1);
        typeDao.save(type2);

        SportEvent event1 = createEvent("event1", type1, province1);
        SportEvent event2 = createEvent("event2", type1, province1);

        eventDao.save(event1);
        eventDao.save(event2);

        Block<SportEvent> expectedBlock = new Block<>(Arrays.asList(event1,event2), false);

        assertEquals(expectedBlock, sportEventService.search(province1.getId(), type1.getId(), LocalDate.now(), LocalDate.of(2025,3,14), 0, 2));

    }
    @Test
    public void testFindNotExistingEvent() throws InstanceNotFoundException{
        Province province1 = new Province("province1");
        Province province2 = new Province("province2");

        provinceDao.save(province1);
        provinceDao.save(province2);

        SportEventType type1 = new SportEventType("carrera");
        SportEventType type2 = new SportEventType("natacion");

        typeDao.save(type1);
        typeDao.save(type2);
        Block<SportEvent> result = sportEventService.search(province1.getId(), type1.getId(), LocalDate.now(), LocalDate.now(), 0, 10);


        assertEquals(false, result.getExistMoreItems());
        assertEquals(0, result.getItems().size());
    }


}

