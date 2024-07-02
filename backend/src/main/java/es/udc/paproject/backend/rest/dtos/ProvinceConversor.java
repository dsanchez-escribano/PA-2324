package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Province;

import java.util.List;
import java.util.stream.Collectors;

public class ProvinceConversor {

    private  ProvinceConversor() {}

    public final static ProvinceDto toProvinceDto(Province province) {
        return  new ProvinceDto(province.getProvinceName(), province.getId());
    }

    public final static List<ProvinceDto> toProvinceDtos(List<Province> provinces) {
        return provinces.stream().map(s -> toProvinceDto(s)).collect(Collectors.toList());
    }
}
