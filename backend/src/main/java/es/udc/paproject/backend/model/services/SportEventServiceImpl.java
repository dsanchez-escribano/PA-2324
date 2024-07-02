package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SportEventServiceImpl implements SportEventService{

    @Autowired
    private SportEventTypeDAO typeDAO;

    @Autowired
    private ProvinceDAO provinceDAO;

    @Autowired
    private SportEventDAO eventDAO;




    @Override
    public Block<SportEvent> search(Long provinceId, Long typeId, LocalDate startDate, LocalDate endDate, int page, int size) {
        LocalDateTime startDate1 = null;
        LocalDateTime endDate1 = null;

        if (startDate != null) {
            startDate1 = startDate.atStartOfDay();
        }
        if (endDate != null) {
            endDate1 = endDate.atTime(23, 59, 59);
        }

        Slice<SportEvent> slice = eventDAO.find(provinceId, typeId, startDate1, endDate1, page, size);
        return new Block<>(slice.getContent(), slice.hasNext());
    }


    @Override
    public SportEvent findEventById(Long id) throws InstanceNotFoundException {

        Optional<SportEvent> event = eventDAO.findById(id);

        if (!event.isPresent()) {
            throw new InstanceNotFoundException("project.entities.event", id);
        }
        return event.get();
    }


    @Override
    public List<SportEventType> findAllSportEventTypes() { return typeDAO.findAll(Sort.by(Sort.Direction.ASC, "typeName"));}

    @Override
    public List<Province> findAllProvinces() { return provinceDAO.findAll(Sort.by(Sort.Direction.ASC, "provinceName"));}


}
