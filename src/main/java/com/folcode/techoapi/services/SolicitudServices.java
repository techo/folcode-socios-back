package com.folcode.techoapi.services;

import com.folcode.techoapi.models.entities.SolicitudDeCambioEntity;
import com.folcode.techoapi.models.entities.dto.SolicitudDto;
import com.folcode.techoapi.models.entities.repositories.SolicitudDeCambioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class SolicitudServices {
    private final SolicitudDeCambioRepository solicitudDeCambioRepository;

    public SolicitudServices(SolicitudDeCambioRepository solicitudDeCambioRepository) {
        this.solicitudDeCambioRepository = solicitudDeCambioRepository;
    }


    public ResponseEntity<SolicitudDeCambioEntity> crearSolicitud(SolicitudDto nueva) {
        SolicitudDeCambioEntity solicitud =
                new SolicitudDeCambioEntity(nueva.getId(),
                        nueva.getFecha() == null ? LocalDate.now() : nueva.getFecha(),
                        nueva.getNombre(),
                        nueva.getApellido(),
                        nueva.getDni(),
                        nueva.getUid(),
                        nueva.getTipo(),
                        nueva.getEmail(),
                        nueva.getDatoACambiar()
                );

        return ResponseEntity.ok(solicitudDeCambioRepository.save(solicitud));
    }

    public List<SolicitudDeCambioEntity> getAllSolicitudes() {

        List<SolicitudDeCambioEntity> solicitudes = new ArrayList<>();
        solicitudDeCambioRepository.findAll().forEach(solicitudes::add);
        return solicitudes;
    }

    public ResponseEntity<String> deleteSolicitud(Long id) {
        try {
            solicitudDeCambioRepository.deleteById(id);
            return new ResponseEntity<>("Se borro Solicitud", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo Borrar", HttpStatus.BAD_REQUEST);
        }
    }
}
