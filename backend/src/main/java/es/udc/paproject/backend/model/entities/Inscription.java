package es.udc.paproject.backend.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inscription {
    private String creditCard;
    private Long id;
    private SportEvent event;
    private User user;
    private int dorsal;
    private LocalDateTime inscriptionDate;
    private boolean picked;
    private int valoration;
    private boolean rated;




    public Inscription() {
    }

    public Inscription(String creditCard, SportEvent event, User user, int dorsal) {
        this.valoration = -1;
        this.creditCard = creditCard;
        this.event = event;
        this.user = user;
        this.dorsal = dorsal;
        this.inscriptionDate = LocalDateTime.now();
        this.picked = false;
        this.rated = false;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sportEventId")
    public SportEvent getEvent() {
        return event;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public int getValoration() {
        return valoration;
    }

    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
