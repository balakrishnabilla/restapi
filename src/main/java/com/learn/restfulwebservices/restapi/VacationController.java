package com.learn.restfulwebservices.restapi;

import com.learn.restfulwebservices.restapi.entity.VacationEntity;
import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.repository.VacationRepository;
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
        Vacation  vacation=new Vacation();
        vacation.setId(save.getId());
        vacation.setDestination(save.getDestination());
        vacation.setName(save.getName());
        vacation.setNoOfDays(save.getNoOfDays());
       return vacation;

    }

    private VacationEntity getVacationEntity(@RequestBody Vacation vacation) {
        VacationEntity  vacationEntity=new VacationEntity();
        vacationEntity.setId(vacation.getId());
        vacationEntity.setDestination(vacation.getDestination());
        vacationEntity.setName(vacation.getName());
        vacationEntity.setNoOfDays(vacation.getNoOfDays());
        return vacationEntity;

    }

}
