package com.learn.restfulwebservices.entity;


import com.learn.restfulwebservices.pojo.Review;
import lombok.Builder;
import lombok.Getter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "vacation")
public class VacationEntity {
    @Id
    @Column(name = "vacationID")
    @GeneratedValue()
    private Long id;
    private String name;
    private String  destination;
    private int noOfDays;
    @OneToMany(mappedBy = "vacationID")
    private List<Review> reviewList;
}
