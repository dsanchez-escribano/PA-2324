package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ProvinceDAO extends CrudRepository <Province, Long> , ListPagingAndSortingRepository<Province, Long> {

}
