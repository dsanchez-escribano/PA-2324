package es.udc.paproject.backend.model.entities;

        import jakarta.persistence.*;

        import java.util.ArrayList;
        import java.util.List;


@Entity
public class SportEventType {
    private String typeName;
    private Long id;


    public SportEventType(){}

    public SportEventType(String type) {
        this.typeName = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
