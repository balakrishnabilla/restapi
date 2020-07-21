package com.learn.restfulwebservices.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue
    private String reviewId;
    private String rating;
    private String comment;
    private String vacationID;
}
