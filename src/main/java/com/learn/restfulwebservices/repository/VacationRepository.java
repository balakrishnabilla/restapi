package com.learn.restfulwebservices.repository;

import com.learn.restfulwebservices.entity.VacationEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VacationRepository extends CrudRepository<VacationEntity, Long> {
}
