package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.CrudRepository;

public interface SportEventDAO extends CrudRepository <SportEvent, Long>, CustomizedSportEventDao{
}
