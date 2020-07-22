package com.learn.restfulwebservices.restapi.service;

import com.learn.restfulwebservices.restapi.entity.VacationEntity;
import com.learn.restfulwebservices.restapi.exception.VacationNotFoundException;
import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VacationService {
    @Autowired
    private VacationRepository vacationRepository;

    public List<Vacation> retriveAllVacations() {
        List<Vacation> list = new ArrayList<>();
        vacationRepository
                .findAll()
                .forEach(
                        v -> {
                            list.add(buildVacationPojo(v));
                        });
        return list;
    }

    public Vacation retriveVacation(@PathVariable("id") Long id) {
        Optional<VacationEntity> vacationEntity = vacationRepository.findById(id);
        if (!vacationEntity.isPresent()) throw new VacationNotFoundException("id-" + id);
        return buildVacationPojo(vacationEntity.get());
    }

    public Long createVacation(@RequestBody Vacation vacation) {
        return vacationRepository.save(getVacationEntity(vacation)).getId();
    }

    public void deleteVacation(@PathVariable long id) {
        vacationRepository.deleteById(id);
    }

    public ResponseEntity<Object> updateVacation(
            @RequestBody Vacation vacation, @PathVariable long id) {

        Optional<VacationEntity> vacationEntityOptional = vacationRepository.findById(id);
        if (!vacationEntityOptional.isPresent()) return ResponseEntity.notFound().build();

        vacation.setId(id);
        vacationRepository.save(getVacationEntity(vacation));
        return ResponseEntity.noContent().build();
    }

    private Vacation buildVacationPojo(VacationEntity save) {
        Vacation vacation = new Vacation();
        vacation.setId(save.getId());
        vacation.setDestination(save.getDestination());
        vacation.setName(save.getName());
        vacation.setNoOfDays(save.getNoOfDays());
        return vacation;
    }

    private VacationEntity getVacationEntity(@RequestBody Vacation vacation) {
        VacationEntity vacationEntity = new VacationEntity();
        vacationEntity.setId(vacation.getId());
        vacationEntity.setDestination(vacation.getDestination());
        vacationEntity.setName(vacation.getName());
        vacationEntity.setNoOfDays(vacation.getNoOfDays());
        return vacationEntity;
    }
}
