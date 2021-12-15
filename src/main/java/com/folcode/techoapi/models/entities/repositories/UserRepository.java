package com.folcode.techoapi.models.entities.repositories;

import com.folcode.techoapi.models.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository <UserEntity,Long>{

}
