package es.udc.paproject.backend.rest.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public class InscriptionDto {

    private String creditCard;
    private Long id;
    private Long eventId;
    private String eventName;
    private int dorsal;
    private LocalDateTime inscriptionDate;
    private boolean picked;
    private int valoration;
    private boolean rateAvailable;
    private boolean rated;

    public InscriptionDto() {
    }

    public InscriptionDto(Long id, String creditCard, Long eventId, String eventName, int dorsal, boolean picked, int valoration,
            LocalDateTime inscriptionDate,LocalDateTime eventDate,boolean eventStarted, boolean rated) {
        this.id = id;
        this.valoration = valoration;
        this.creditCard = creditCard;
        this.eventId = eventId;
        this.eventName = eventName;
        this.dorsal = dorsal;
        this.inscriptionDate = inscriptionDate;
        this.picked = picked;
        this.rateAvailable = !rated && (Duration.between(eventDate, LocalDateTime.now()).toDays() < 15) && eventStarted;
        this.rated = rated;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public LocalDateTime getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(LocalDateTime date) {
        this.inscriptionDate = date;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getValoration() {
        return valoration;
    }

    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    public boolean isRateAvailable() {
        return rateAvailable;
    }

    public void setRateAvailable(boolean rateAvailable) {
        this.rateAvailable = rateAvailable;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
