package com.folcode.techoapi.models.entities.repositories;

import com.folcode.techoapi.models.entities.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SocioRepository extends JpaRepository<SocioEntity, Long> {
    SocioEntity findByDni(String dni);
    SocioEntity findByUid(String uid);


    List<SocioEntity> findByDeleted(boolean isDeleted);
}
