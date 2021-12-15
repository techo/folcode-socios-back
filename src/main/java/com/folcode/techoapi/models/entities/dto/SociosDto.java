package com.folcode.techoapi.models.entities.dto;


import java.util.List;

public class SociosDto {
private List<SocioDto> socios;


    public SociosDto(List<SocioDto> socioDtoList) {
    }

    public List<SocioDto> getSocios() {
        return socios;
    }

    public void setSocios(List<SocioDto> socios) {
        this.socios = socios;
    }
}
