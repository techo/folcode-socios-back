package com.folcode.techoapi.controllers;

import com.folcode.techoapi.models.entities.SocioEntity;
import com.folcode.techoapi.models.entities.dto.MontoDto;
import com.folcode.techoapi.models.entities.dto.SocioDto;
import com.folcode.techoapi.services.SociosService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socios")
public class SociosController {

    private final SociosService sociosService;


    public SociosController(SociosService sociosService) {
        this.sociosService = sociosService;


    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @GetMapping("/listado")
    public ResponseEntity<List<SocioEntity>>findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false")boolean isDeleted) {

        return ResponseEntity.ok(sociosService.findAll(isDeleted));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws FirebaseAuthException {
        return (sociosService.deleteSocios(id));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @PostMapping("")
    public ResponseEntity<SocioDto> crearSocio(@RequestBody SocioDto nuevo) throws FirebaseAuthException {
        return ResponseEntity.ok(sociosService.createSocio(nuevo));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @PutMapping("/{id}")
    public ResponseEntity<String> agregaSocio(@PathVariable("id") Long id, @RequestBody SocioDto modifica) {
        return (sociosService.updateSocio(modifica, id));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @GetMapping("/muestramonto/{id}")
    public ResponseEntity<MontoDto> getMontoByUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sociosService.getMontoByUser(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @PutMapping("/modificamonto/{id}")
    public ResponseEntity<String> updateMonto(@PathVariable("id") Long id, @RequestParam Long monto) {
        return (sociosService.updateMonto(monto, id));

    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @GetMapping("/{id}")
    public ResponseEntity<SocioDto> getSocioPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sociosService.getSocioPorId(id), HttpStatus.OK);
    }

}
