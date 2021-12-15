package com.folcode.techoapi.models.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioDto {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private Long telef;
    private String mail;
    private String provincia;
    private Long monto;
    private String tarjeta;
    private Long  numTarjeta;
    private String uid;
    private boolean deleted;


    public boolean isDeleted() {
        return deleted;
    }
}
