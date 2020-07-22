package com.learn.restfulwebservices.restapi.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Review {
    private Long reviewId;
    private String rating;
    private String comment;
}
