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

import javax.transaction.Transactional;
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

    @Transactional
    public ResponseEntity updateReview(Review review, Long reviewID, Long vacationID) {
        Optional<VacationEntity> optionalVacationEntity = vacationRepository.findById(vacationID);
        ReviewEntity reviewEntity = getReviewEntity(review);
        if (optionalVacationEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVacationEntity.get();
            reviewEntity.setVacationEntity(vacationEntity);
            reviewRepository.save(reviewEntity);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }



    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public Long createReview(Long vacationID, Review review) {

        ReviewEntity reviewEntity = getReviewEntity(review);

        Optional<VacationEntity> optionalVacationEntity = vacationRepository.findById(vacationID);
        if (optionalVacationEntity.isPresent()) {
            VacationEntity vacationEntity = optionalVacationEntity.get();
            reviewEntity.setVacationEntity(vacationEntity);
            reviewRepository.save(reviewEntity);
        }
        return null;

    }

    public Optional<Review> retrieveReview(Long vacationID, final Long reviewID) {

        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(reviewID);
        if (optionalReviewEntity.isPresent()) {
            return Optional.of(buildReviewPojo(optionalReviewEntity.get()));
        }
        return Optional.empty();

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
