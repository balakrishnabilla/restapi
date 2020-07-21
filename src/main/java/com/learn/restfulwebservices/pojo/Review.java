package com.learn.restfulwebservices.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Long reviewId;
    private String rating;
    private String comment;

}
