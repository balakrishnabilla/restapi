package com.learn.restfulwebservices.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "REVIEW")
public class ReviewEntity {
    @Id
    @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long reviewId;
    private String rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name="VACATION_ID")
    private VacationEntity vacationEntity;
}
