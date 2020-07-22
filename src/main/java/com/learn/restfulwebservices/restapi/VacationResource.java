package com.learn.restfulwebservices.restapi;

import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class VacationResource {
    @Autowired
    private VacationService vacationService;

    @GetMapping(path = "/")
    public String vacations() {
        return "Welcome to ACME VacationEntity portal";
    }

    @GetMapping("/vacations")
    public List<Vacation> retriveAllVacations() {
        return vacationService.retriveAllVacations();
    }

    @GetMapping(path = "/vacations/{id}")
    public Vacation retriveVacation(@PathVariable("id") Long id) {
        return vacationService.retriveVacation(id);
    }

    @PostMapping(path = "/vacations")
    public ResponseEntity<Object> createVacation(@RequestBody Vacation vacation) {
        Long vacationId = vacationService.createVacation(vacation);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(vacationId)
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/vacations/{id}")
    public void deleteVacation(@PathVariable long id) {
        vacationService.deleteVacation(id);
    }

    @PutMapping("/vacations/{id}")
    public ResponseEntity<Object> updateVacation(
            @RequestBody Vacation vacation, @PathVariable Long id) {
        vacationService.updateVacation(vacation, id);
        return ResponseEntity.noContent().build();
    }
}
