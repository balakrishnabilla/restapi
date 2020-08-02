package com.learn.restfulwebservices.restapi.repository;

import com.learn.restfulwebservices.restapi.entity.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
     /* @Query("select * from review join review.vacationID=" )
      public ReviewEntity findByProductId(Long vacationID);*/
}
