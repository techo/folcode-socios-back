package com.folcode.techoapi.models.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Socios")
@Data
@AllArgsConstructor
@NoArgsConstructor

@SQLDelete(sql = "UPDATE socios SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class SocioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private Long telef;
    private String mail;
    private String provincia;
    private Long Monto;
    private String tarjeta;
    private Long numTarjeta;
    private String uid;
    private boolean deleted=false;

}
