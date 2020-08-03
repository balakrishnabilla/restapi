package com.learn.restfulwebservices.restapi;

import com.learn.restfulwebservices.restapi.pojo.Review;
import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vacations/{vacationId}/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService  reviewService;

    @GetMapping()
    public List<Review> retrieveReviews( @PathVariable long vacationId ) {
        return reviewService.retrieveAllReviews(vacationId);
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity<Review> retrieveReview(@PathVariable ("vacationId") Long vacationId  ,@PathVariable("reviewId") Long reviewId) {
         Optional<Review> optionalReview=reviewService.retrieveReview(vacationId,reviewId);
         if(optionalReview.isPresent()){

           return  ResponseEntity.status(HttpStatus.OK).body(optionalReview.get());
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping()
    public ResponseEntity<Object> createVacation(@PathVariable ("vacationId") Long vacationId ,@RequestBody Review review) {

        Long reviewId = reviewService.createReview(vacationId, review);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{reviewId}")
                        .buildAndExpand(reviewId)
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Object>  deleteVacation(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateVacation(
            @RequestBody Review review, @PathVariable("reviewId") Long reviewId,@PathVariable ("vacationId") Long vacationId ) {
        reviewService.updateReview(review, reviewId, vacationId);
        return ResponseEntity.noContent().build();  // Should we send back no content or updated response.
    }

}
