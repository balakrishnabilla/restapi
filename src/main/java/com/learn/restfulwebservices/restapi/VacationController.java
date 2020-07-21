package com.learn.restfulwebservices.restapi;

import com.learn.restfulwebservices.entity.VacationEntity;
import com.learn.restfulwebservices.pojo.Vacation;
import com.learn.restfulwebservices.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class VacationController {
    @Autowired
    private VacationRepository vacationRepository;

    @GetMapping(path = "/")
    public String vacations() {
        return "Welcome to ACME VacationEntity portal";
    }

    @GetMapping(path = "/vacations/{id}")
    public Vacation vacation(@PathVariable("id") Long id) {
        Optional<VacationEntity> vacationEntity =vacationRepository.findById(id);
          if(vacationEntity.isPresent()){
              return   buildVactionPojo(vacationEntity.get());
          }else{
              return null;
          }
    }


    @PostMapping(path = "/vacations" )
    public Vacation createVacation(@RequestBody Vacation vacation) {

        return buildVactionPojo(vacationRepository.save(getVacationEntity(vacation)));
    }

    private Vacation buildVactionPojo(VacationEntity save) {
       return Vacation.builder()
                .id(save.getId())
                .name(save.getName())
                .destination(save.getDestination())
                .noOfDays(save.getNoOfDays())
                .reviewList(save.getReviewList())
                .build();
    }

    private VacationEntity getVacationEntity(@RequestBody Vacation vacation) {
        return VacationEntity.builder()
                .id(vacation.getId())
                .name(vacation.getName())
                .destination(vacation.getDestination())
                .noOfDays(vacation.getNoOfDays())
                .reviewList(vacation.getReviewList())
                .build();
    }

}
