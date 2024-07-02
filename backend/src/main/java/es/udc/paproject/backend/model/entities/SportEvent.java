package es.udc.paproject.backend.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SportEvent {
    private String sportEventName;
    private Long id;
    private String description;
    private LocalDateTime date;
    private BigDecimal price;
    private int participants;
    private String location;
    private int spots;
    private int sumValoraciones;
    private int valoraciones;
    private SportEventType type;
    private Province province;
    private long version;

    public SportEvent() {
    }

    public SportEvent(String sportEventName, String description, LocalDateTime Date, BigDecimal price, String location,
            int spots, SportEventType type, Province province) {
        this.sportEventName = sportEventName;
        this.description = description;
        this.date = Date;
        this.participants = 0;
        this.price = price;
        this.location = location;
        this.spots = spots;
        this.type = type;
        this.province = province;
        this.sumValoraciones = 0;
        this.valoraciones = 0;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSportEventName() {
        return sportEventName;
    }

    public void setSportEventName(String sportEventName) {
        this.sportEventName = sportEventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSpots() {
        return spots;
    }

    public void setSpots(int spots) {
        this.spots = spots;
    }

    public int getSumValoraciones() {
        return sumValoraciones;
    }

    public void setSumValoraciones(int sumValoraciones) {
        this.sumValoraciones = sumValoraciones;
    }

    public int getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(int valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sportEventTypeId")
    public SportEventType getType() {
        return type;
    }

    public void setType(SportEventType type) {
        this.type = type;
    }

    public boolean hasStarted() {
        return LocalDateTime.now().isAfter(date);
    }
}
