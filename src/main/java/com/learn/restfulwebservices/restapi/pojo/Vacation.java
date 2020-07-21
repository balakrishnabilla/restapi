package com.learn.restfulwebservices.restapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Vacation {
    private Long id;
    private String name;
    private String  destination;
    private int noOfDays;
    private List<Review> reviewList;

}
