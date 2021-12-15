package com.folcode.techoapi.mappers;

import com.folcode.techoapi.models.entities.SocioEntity;
import com.folcode.techoapi.models.entities.dto.SocioDto;
import org.springframework.stereotype.Component;

@Component
public class SocioMapper {
    public SocioEntity mapSocioDtoToSocioEntity(SocioDto newSocioDto) {
        return new SocioEntity(
                newSocioDto.getId(),
                newSocioDto.getDni(),
                newSocioDto.getNombre(),
                newSocioDto.getApellido(),
                newSocioDto.getTelef(),
                newSocioDto.getMail(),
                newSocioDto.getProvincia(),
                newSocioDto.getMonto(),
                newSocioDto.getTarjeta(),
                newSocioDto.getNumTarjeta(),
                newSocioDto.getUid(),
                newSocioDto.isDeleted());
    }

    public SocioDto entityToDto(SocioEntity newSocioEntity) {
        return new SocioDto(
                newSocioEntity.getId(),
                newSocioEntity.getDni(),
                newSocioEntity.getNombre(),
                newSocioEntity.getApellido(),
                newSocioEntity.getTelef(),
                newSocioEntity.getMail(),
                newSocioEntity.getProvincia(),
                newSocioEntity.getMonto(),
                newSocioEntity.getTarjeta(),
                newSocioEntity.getNumTarjeta(),
                newSocioEntity.getUid(),
                newSocioEntity.isDeleted());

    }
}
