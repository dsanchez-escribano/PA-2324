package es.udc.paproject.backend.rest.dtos;

public class ProvinceDto {

    private String provinceName;
    private Long id;

    public ProvinceDto(){}
    public  ProvinceDto(String provinceName){
            this.provinceName = provinceName;
        }

    public ProvinceDto(String provinceName, Long id) {
        this.provinceName = provinceName;
        this.id = id;
    }



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


