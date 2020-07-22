package com.learn.restfulwebservices.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vacation")
public class VacationEntity {
    @Id
    @Column(name = "vacationID")
    @GeneratedValue()
    private Long id;

    private String name;
    private String destination;
    private int noOfDays;

    @OneToMany
    // @JoinTable(name = "review", joinColumns = @JoinColumn(name = "vacationID"), inverseJoinColumns
    // = @JoinColumn(name = "PHONE_ID"))
    private List<ReviewEntity> reviewList;
}
