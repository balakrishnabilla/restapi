package com.learn.restfulwebservices.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Vacation {
    private Long id;
    private String name;
    private String  destination;
    private int noOfDays;
    private List<Review> reviewList;
}
