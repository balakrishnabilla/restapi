package com.learn.restfulwebservices.restapi.controller;

import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/vacations")
public class VacationResource {
    @Autowired
    private VacationService vacationService;

    @GetMapping()
    public List<Vacation> retrieveVacations() {
        return vacationService.retrieveAllVacations();
    }

    @GetMapping(path = "/{id}")
    public Vacation retrieveVacation(@PathVariable("id") Long id) {
        return vacationService.retrieveVacation(id);
    }

    @PostMapping()
    public ResponseEntity<Object> createVacation(@RequestBody Vacation vacation) {

        Long vacationId = vacationService.createVacation(vacation);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vacationId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteVacation(@PathVariable long id) {
        vacationService.deleteVacation(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVacation(
            @RequestBody Vacation vacation, @PathVariable Long id) {
        vacationService.updateVacation(vacation, id);
        return ResponseEntity.noContent().build();  // Should we send back no content or updated response.
    }

}
