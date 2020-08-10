package com.learn.restfulwebservices.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "VACATION")
public class VacationEntity {
    @Id
    @Column(name = "VACATION_ID")
    @GeneratedValue()
    private Long id;

    private String name;
    private String destination;
    @Column(name = "NO_OF_DAYS")
    private int noOfDays;

    @OneToMany(mappedBy = "vacationEntity")
    private List<ReviewEntity> reviewList = new ArrayList<>();
}
