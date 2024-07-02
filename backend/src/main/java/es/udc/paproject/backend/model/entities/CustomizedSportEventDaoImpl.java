package es.udc.paproject.backend.model.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class CustomizedSportEventDaoImpl implements CustomizedSportEventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<SportEvent> find(Long provinceId, Long sportEventTypeId, LocalDateTime startDate,
            LocalDateTime endDate, int page, int size) {

        String queryString = "SELECT s FROM SportEvent s WHERE 1=1";

        if (provinceId != null) {
            queryString += " AND s.province.id = :provinceId";
        }
        if (sportEventTypeId != null) {
            queryString += " AND s.type.id = :sportEventTypeId";
        }
        if (startDate != null) {
            queryString += " AND s.date >= :startDate";
        }
        if (endDate != null) {
            queryString += " AND s.date <= :endDate";
        }

        queryString += " ORDER BY s.date DESC";

        Query query = entityManager.createQuery(queryString).setFirstResult(page * size).setMaxResults(size + 1);

        if (provinceId != null) {
            query.setParameter("provinceId", provinceId);
        }
        if (sportEventTypeId != null) {
            query.setParameter("sportEventTypeId", sportEventTypeId);
        }
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }

        List<SportEvent> events = query.getResultList();
        boolean hasNext = events.size() == (size + 1);

        if (hasNext) {
            events.remove(events.size() - 1);
        }

        return new SliceImpl<>(events, PageRequest.of(page, size), hasNext);
    }

}
