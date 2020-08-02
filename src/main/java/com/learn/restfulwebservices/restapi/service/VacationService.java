package com.learn.restfulwebservices.restapi.service;

import com.learn.restfulwebservices.restapi.entity.ReviewEntity;
import com.learn.restfulwebservices.restapi.entity.VacationEntity;
import com.learn.restfulwebservices.restapi.exception.ResourceNotFoundException;
import com.learn.restfulwebservices.restapi.pojo.Review;
import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class VacationService {
    @Autowired
    private VacationRepository vacationRepository;

    public List<Vacation> retrieveAllVacations() {

        List<Vacation> list = new ArrayList<>();
        vacationRepository
                .findAll()
                .forEach(
                        v -> {
                            list.add(buildVacationPojo(v));
                        });


        return list;
    }

    public Vacation retrieveVacation( Long id) {
        Optional<VacationEntity> vacationEntity = vacationRepository.findById(id);
        if (!vacationEntity.isPresent()) throw new ResourceNotFoundException("id-" + id);
        return buildVacationPojo(vacationEntity.get());
    }

    public Long createVacation( Vacation vacation) {
        return vacationRepository.save(getVacationEntity(vacation)).getId();
    }

    public void deleteVacation( long id) {
        vacationRepository.deleteById(id);
    }

    public ResponseEntity<Object> updateVacation(Vacation vacation, long id) {

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

        if(save.getReviewList()!= null && !save.getReviewList().isEmpty()) {
            vacation.setReviewList(new ArrayList<>());
            for (ReviewEntity reviewEntity : save.getReviewList()) {
                Review review = new Review();
                review.setReviewId(reviewEntity.getReviewId());
                review.setComment(reviewEntity.getComment());
                review.setRating(reviewEntity.getRating());
                vacation.getReviewList().add(review);
            }
        }
        return vacation;
    }

    private VacationEntity getVacationEntity(Vacation vacation) {
        VacationEntity vacationEntity = new VacationEntity();
        vacationEntity.setId(vacation.getId());
        vacationEntity.setDestination(vacation.getDestination());
        vacationEntity.setName(vacation.getName());
        vacationEntity.setNoOfDays(vacation.getNoOfDays());
        if(vacation.getReviewList()!= null && !vacation.getReviewList().isEmpty()) {
            vacationEntity.setReviewList(new ArrayList<>());
            for (Review review : vacation.getReviewList()) {
                ReviewEntity reviewEntity = new ReviewEntity();
                reviewEntity.setComment(review.getComment());
                reviewEntity.setRating(review.getRating());
                vacationEntity.getReviewList().add(reviewEntity);
            }
        }
        return vacationEntity;
    }
}
