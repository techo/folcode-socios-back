package com.folcode.techoapi.models.entities.dto;

import java.util.List;

public class SolicitudesDto {
    private List<SolicitudDto> solicitudes;

    public SolicitudesDto(List<SolicitudDto> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
