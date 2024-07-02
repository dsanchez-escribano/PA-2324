package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;

public interface CustomizedSportEventDao {
    Slice<SportEvent> find(Long provinceId, Long sportEventType, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
}
