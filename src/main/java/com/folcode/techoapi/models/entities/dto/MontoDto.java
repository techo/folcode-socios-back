package com.folcode.techoapi.models.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MontoDto {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private Long monto;
}
