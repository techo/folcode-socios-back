package com.folcode.techoapi.controllers;




import com.folcode.techoapi.models.entities.SolicitudDeCambioEntity;
import com.folcode.techoapi.models.entities.dto.SolicitudDto;
import com.folcode.techoapi.services.SolicitudServices;
import com.sun.istack.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudServices solicitudServices;

    public SolicitudController(SolicitudServices solicitudServices) {
        this.solicitudServices = solicitudServices;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @PostMapping("")
    public ResponseEntity<SolicitudDeCambioEntity> crearSolicitud(@RequestBody @NotNull SolicitudDto nueva) {
        return (solicitudServices.crearSolicitud(nueva));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @GetMapping("/listado")
    public ResponseEntity<List<SolicitudDeCambioEntity>> getAllSocios() {

        return ResponseEntity.ok(solicitudServices.getAllSolicitudes());
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSolicitud(@PathVariable("id") @NotNull  Long id) {
        return (solicitudServices.deleteSolicitud(id));
    }


}
