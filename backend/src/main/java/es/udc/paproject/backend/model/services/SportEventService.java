package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportEvent;
import es.udc.paproject.backend.model.entities.SportEventType;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface SportEventService {
   public List<Province> findAllProvinces();
   public List<SportEventType> findAllSportEventTypes();
   Block<SportEvent> search(Long provinceId, Long typeName, LocalDate startDate, LocalDate endDate, int page, int size);

   SportEvent findEventById(Long id) throws InstanceNotFoundException;

}



