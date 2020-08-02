package com.learn.restfulwebservices.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue
    private Long reviewId;
    private String rating;
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacationID", referencedColumnName = "vacationID")
    private VacationEntity vacationEntity;
}
