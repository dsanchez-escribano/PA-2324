package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface SportEventTypeDAO
        extends CrudRepository<SportEventType, Long>, ListPagingAndSortingRepository<SportEventType, Long> {
}