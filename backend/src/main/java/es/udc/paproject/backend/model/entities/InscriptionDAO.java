package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InscriptionDAO
        extends CrudRepository<Inscription, Long> {
    Optional<Inscription> findByEventIdAndUserId(Long eventId, Long userId);

    Slice<Inscription> findByUserIdOrderByInscriptionDateDesc(Long userId, Pageable pageable);
}
