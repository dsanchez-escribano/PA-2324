package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportEventType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.grammars.hql.HqlParser.LocalDateTimeContext;

public class SportEventDto {
    private String sportEventName;
    private Long id;
    private String description;
    private LocalDateTime date;
    private BigDecimal price;
    private int participants;
    private String location;
    private int spots;
    private Long typeId;
    private Long provinceId;
    private BigDecimal media;
    private boolean valorated;
    private boolean availability;
    private boolean started;

    public SportEventDto(String sportEventName, Long id, String description, LocalDateTime date, BigDecimal price,
            int participants, String location, int spots, int sumValoraciones, int valoraciones, Long typeId,
            Long provinceId, boolean started) {
        this.sportEventName = sportEventName;
        this.id = id;
        this.description = description;
        this.date = date;
        this.price = price;
        this.participants = participants;
        this.location = location;
        this.spots = spots;
        this.typeId = typeId;
        this.provinceId = provinceId;
        this.media = valoraciones == 0 ? BigDecimal.valueOf(0)
                : BigDecimal.valueOf(sumValoraciones).divide(BigDecimal.valueOf(valoraciones), 1, RoundingMode.CEILING);
        this.valorated = !(valoraciones == 0);
        this.availability = (!started && participants != spots && (Duration.between(LocalDateTime.now(), date).toHours() > 24));
        this.started = started;
    }

    public void setSportEventName(String sportEventName) {
        this.sportEventName = sportEventName;
    }

    public String getSportEventName() {
        return sportEventName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getParticipants() {
        return participants;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setSpots(int spots) {
        this.spots = spots;
    }

    public int getSpots() {
        return spots;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailable(boolean availability) {
        this.availability = availability;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isValorated() {
        return valorated;
    }

    public void setValorated(boolean valorated) {
        this.valorated = valorated;
    }

}
