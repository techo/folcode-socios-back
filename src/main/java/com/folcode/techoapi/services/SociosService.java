package com.folcode.techoapi.services;


import com.folcode.techoapi.mappers.SocioMapper;
import com.folcode.techoapi.models.entities.SocioEntity;
import com.folcode.techoapi.models.entities.SolicitudDeCambioEntity;
import com.folcode.techoapi.models.entities.dto.MontoDto;
import com.folcode.techoapi.models.entities.dto.SocioDto;
import com.folcode.techoapi.models.entities.repositories.SocioRepository;
import com.folcode.techoapi.models.entities.repositories.SolicitudDeCambioRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SociosService {
    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;
    private final SolicitudDeCambioRepository solicitudDeCambioRepository;

    private static EntityManager entityManager;

    public SociosService(SocioRepository socioRepository, SocioMapper socioMapper, SolicitudDeCambioRepository solicitudDeCambioRepository) {
        this.socioRepository = socioRepository;


        this.socioMapper = socioMapper;
        this.solicitudDeCambioRepository = solicitudDeCambioRepository;

    }

    /*public Iterable<Product> findAll(<strong>boolean isDeleted</strong>){
           <strong> Session session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("deletedProductFilter");
            filter.setParameter("isDeleted", isDeleted);
            Iterable<Product> products =  productRepository.findAll();
            session.disableFilter("deletedProductFilter");
            return products;</strong>
        }*/


    public List<SocioEntity> findAll(boolean isDeleted) {
        System.out.println(isDeleted);
        return socioRepository.findAll();
    }

//    public List<SocioEntity> findAllFilter(boolean isDeleted) {
//        Session session = entityManager.unwrap(Session.class);
//        Filter filter = session.enableFilter("deletedSocioFilter");
//        System.out.println(filter);
//        filter.setParameter("isDeleted", isDeleted);
//        List<SocioEntity> socios = socioRepository.findAll();
//        session.disableFilter("deleteSocioFilter");
//        return socios;
//    }

    public ResponseEntity<String> deleteSocios(Long id) throws FirebaseAuthException {
        Optional<SocioEntity> sociodeleteOptional = socioRepository.findById(id);

        if (sociodeleteOptional.isPresent()) {
            SocioEntity sociodelete = sociodeleteOptional.get();

            socioRepository.delete(sociodelete);

            FirebaseAuth.getInstance().deleteUser(sociodelete.getUid());

            return new ResponseEntity<>("Se borro usuario", HttpStatus.OK);
        }

        return new ResponseEntity<>("No se encontro usuario", HttpStatus.OK);

    }

    public SocioDto createSocio(SocioDto nuevo) throws FirebaseAuthException {
        /*if claims* getClaims().get("admin")*/

        SocioEntity socio = socioMapper.mapSocioDtoToSocioEntity(nuevo);
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(nuevo.getMail());

            if (nuevo.getMail().equals(userRecord.getEmail())) {
                if (socio.getUid() == null) {
                    socio.setUid(userRecord.getUid());
                }
            }
        } catch (Exception ignored) {
        }
        return socioMapper.entityToDto(socioRepository.save(socio));
    }

    /*modificar el buscar con el id no el uid*/
    public ResponseEntity<String> updateSocio(SocioDto modifica, Long id) {

        Optional<SocioEntity> socioEntityOptional = socioRepository.findById(id);

        if (socioEntityOptional.isPresent()) {
            SocioEntity update = socioEntityOptional.get();

            update.setDni(modifica.getDni());
            update.setNombre(modifica.getNombre());
            update.setApellido(modifica.getApellido());
            update.setTelef(modifica.getTelef());
            update.setMail(modifica.getMail());
            update.setMonto(modifica.getMonto());
            update.setProvincia(modifica.getProvincia());
            update.setTarjeta(modifica.getTarjeta());
            update.setNumTarjeta(modifica.getNumTarjeta());
            update.setUid(modifica.getUid());

            socioRepository.save(update);
            return new ResponseEntity<>("Se actualizo Socio", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró", HttpStatus.NOT_FOUND);
        }
    }


    public MontoDto getMontoByUser(Long id) {
        Optional<SocioEntity> socioOptional = socioRepository.findById(id);

        if (socioOptional.isPresent()) {
            SocioEntity socioMonto = socioOptional.get();


            return new MontoDto(socioMonto.getId(), socioMonto.getDni(), socioMonto.getNombre(), socioMonto.getApellido(), socioMonto.getMonto());
        }

        return null;
    }


    public ResponseEntity<String> updateMonto(Long monto, Long id) {

        Optional<SocioEntity> socioEntityOptional = socioRepository.findById(id);

        if (socioEntityOptional.isPresent()) {
            SocioEntity update = socioEntityOptional.get();

            update.setDni(update.getDni());
            update.setNombre(update.getNombre());
            update.setApellido(update.getApellido());
            update.setTelef(update.getTelef());
            update.setMail(update.getMail());
            update.setMonto(monto);
            update.setProvincia(update.getProvincia());
            update.setTarjeta(update.getTarjeta());
            update.setNumTarjeta(update.getNumTarjeta());
            update.setUid(update.getUid());

            SolicitudDeCambioEntity nueva = new SolicitudDeCambioEntity(null, LocalDate.now(), update.getNombre(), update.getApellido(), update.getDni(), update.getUid(), "Se realizo un cambio de monto de donacion", update.getMail(), monto.toString());

            solicitudDeCambioRepository.save(nueva);
            socioRepository.save(update);
            return new ResponseEntity<>("Se actualizo Monto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró", HttpStatus.NOT_FOUND);
        }
    }


    public SocioDto getSocioPorId(Long id) {

        Optional<SocioEntity> userOptional = socioRepository.findById(id);
        if (userOptional.isPresent()) {

            SocioEntity user = userOptional.get();

            return new SocioDto(user.getId(),
                    user.getDni(),
                    user.getNombre(),
                    user.getApellido(),
                    user.getTelef(),
                    user.getMail(),
                    user.getProvincia(),
                    user.getMonto(),
                    user.getTarjeta(),
                    user.getNumTarjeta(),
                    user.getUid(),
                    user.isDeleted()
            );
        }
        System.out.println("No se encontro");
        return null;

    }
}


