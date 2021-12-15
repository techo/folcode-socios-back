package com.folcode.techoapi.models.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "Solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDeCambioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fecha = LocalDate.now();
    private String nombre;
    private String apellido;
    private String dni;
    private String uid;
    private String tipo;
    private String email;
    private String datoACambiar;


}
