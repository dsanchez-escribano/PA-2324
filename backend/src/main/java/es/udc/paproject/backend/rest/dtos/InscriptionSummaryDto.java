package es.udc.paproject.backend.rest.dtos;

public class InscriptionSummaryDto {

    private Long id;
    private Long eventId;
    private String creditCard;
    private int valoration;
    
    
    public InscriptionSummaryDto(Long id, Long eventId, String creditCard, int valoration) {
        this.id = id;
        this.eventId = eventId;
        this.creditCard = creditCard;
        this.valoration = valoration;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEventId() {
        return eventId;
    }
    
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    
    public String getCreditCard() {
        return creditCard;
    }
    
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    
    public int getValoration() {
        return valoration;
    }
    
    public void setValoration(int valoration) {
        this.valoration = valoration;
    }
    
}
