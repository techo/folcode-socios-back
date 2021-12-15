package com.folcode.techoapi.models.entities.repositories;

import com.folcode.techoapi.models.entities.SolicitudDeCambioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudDeCambioRepository extends CrudRepository<SolicitudDeCambioEntity, Long> {

}
