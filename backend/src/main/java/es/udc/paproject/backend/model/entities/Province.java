package es.udc.paproject.backend.model.entities;

import jakarta.persistence.*;

@Entity
public class Province {
    private String provinceName;
    private Long id;

    public Province(){}
    public Province(String provinceName, Long id) {
        this.provinceName = provinceName;
        this.id = id;
    }

    public  Province(String provinceName){
        this.provinceName = provinceName;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
