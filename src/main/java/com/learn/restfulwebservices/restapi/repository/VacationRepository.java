package com.learn.restfulwebservices.restapi.repository;

import com.learn.restfulwebservices.restapi.entity.VacationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VacationRepository extends CrudRepository<VacationEntity, Long> {
}
