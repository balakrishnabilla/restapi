package com.learn.restfulwebservices.restapi.service;

import com.learn.restfulwebservices.restapi.entity.ReviewEntity;
import com.learn.restfulwebservices.restapi.entity.VacationEntity;
import com.learn.restfulwebservices.restapi.exception.ResourceNotFoundException;
import com.learn.restfulwebservices.restapi.pojo.Review;
import com.learn.restfulwebservices.restapi.repository.ReviewRepository;
import com.learn.restfulwebservices.restapi.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private VacationRepository vacationRepository;

    public ResponseEntity updateReview(Review review,Long reviewID ,Long vacationID) {
        Optional<VacationEntity> optionalVacationEntity = vacationRepository.findById(vacationID);
        if (optionalVacationEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVacationEntity.get();
            Optional<ReviewEntity> optionalReviewEntity = vacationEntity.getReviewList().stream().filter(reviewEntity -> reviewID.equals(reviewEntity.getReviewId())).findFirst();
            if (optionalReviewEntity.isPresent()) {
                ReviewEntity reviewEntity = optionalReviewEntity.get();
                reviewEntity.setRating(review.getRating());
                reviewEntity.setComment(review.getComment());
                reviewRepository.save(getReviewEntity(review));
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }

    public Long createReview(Long vacationID, Review review) {

        Optional<VacationEntity> optionalVacationEntity = vacationRepository.findById(vacationID);
        if (optionalVacationEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVacationEntity.get();
            vacationEntity.getReviewList().add(getReviewEntity(review));
            vacationRepository.save(vacationEntity);

        }
        return null;

    }

    public Review retrieveReview(Long vacationID, final Long reviewID) {
        Optional<VacationEntity> optionalVacationEntity = vacationRepository.findById(vacationID);
        if (optionalVacationEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVacationEntity.get();
            Optional<ReviewEntity> optionalReviewEntity = vacationEntity.getReviewList().stream().filter(reviewEntity -> reviewID.equals(reviewEntity.getReviewId())).findFirst();
            if (optionalReviewEntity.isPresent()) {
                return buildReviewPojo(optionalReviewEntity.get());
            }

        }
        return null;
    }

    public List<Review> retrieveAllReviews(Long vacationID) {
        Optional<VacationEntity> optionalVactionEntity = vacationRepository.findById(vacationID);
        if (optionalVactionEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVactionEntity.get();
            List<ReviewEntity> reviewEntities = vacationEntity.getReviewList();
            return reviewEntities.stream().map(reviewEntity -> buildReviewPojo(reviewEntity)).collect(Collectors.toList());
        } else {
            List<Review> reviewList = new ArrayList<>();
            return reviewList;
        }

    }

    private ReviewEntity getReviewEntity(Review review) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewId(review.getReviewId());
        reviewEntity.setComment(review.getComment());
        review.setRating(review.getRating());
        return reviewEntity;
    }


    private Review buildReviewPojo(ReviewEntity reviewEntity) {
        Review review = new Review();
        review.setReviewId(reviewEntity.getReviewId());
        review.setRating(reviewEntity.getRating());
        review.setComment(reviewEntity.getComment());
        review.setReviewId(reviewEntity.getReviewId());

        return review;
    }
}
