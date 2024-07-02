package es.udc.paproject.backend.rest.dtos;

public class SportEventTypeDto {

    private String typeName;
    private Long id;


    public SportEventTypeDto(){}

    public SportEventTypeDto(String typeName, Long id){
        this.typeName= typeName;
        this.id=id;
    }

    public SportEventTypeDto(String type) {
        this.typeName = type;
    }

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
