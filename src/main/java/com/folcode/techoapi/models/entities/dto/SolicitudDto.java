package com.folcode.techoapi.models.entities.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    private Long id;
    private LocalDate fecha;
    private String nombre;
    private String apellido;
    private String dni;
    private String uid;
    private String tipo;
    private String email;
    private String datoACambiar;

}
