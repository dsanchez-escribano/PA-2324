package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class SportEventSummaryDto {

    private Long id;
    private String sportEventName;
    private LocalDateTime date;
    private Long typeId;
    private Long provinceId;
    private boolean valorated;
    private BigDecimal media;
    private boolean rateAble;

    public SportEventSummaryDto() {
    }

    public SportEventSummaryDto(String sportEventName, Long id, LocalDateTime date, Long typeId, Long provinceId,
            int sumValoraciones, int valoraciones, boolean started) {
        this.sportEventName = sportEventName;
        this.id = id;
        this.date = date;
        this.typeId = typeId;
        this.provinceId = provinceId;
        this.valorated = !(valoraciones == 0);
        this.media = valoraciones == 0 ? BigDecimal.valueOf(0)
                : BigDecimal.valueOf(sumValoraciones).divide(BigDecimal.valueOf(valoraciones), 1, RoundingMode.CEILING);
        this.rateAble = (started && (Duration.between(date, LocalDateTime.now()).toDays() < 15));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return sportEventName;
    }

    public void setName(String sportEventName) {
        this.sportEventName = sportEventName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSportEventName() {
        return sportEventName;
    }

    public void setSportEventName(String sportEventName) {
        this.sportEventName = sportEventName;
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

    public boolean isValorated() {
        return valorated;
    }

    public void setValorated(boolean valorated) {
        this.valorated = valorated;
    }

    public boolean isRateAble() {
        return rateAble;
    }

    public void setRateAble(boolean rateAble) {
        this.rateAble = rateAble;
    }

}
