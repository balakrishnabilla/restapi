package com.learn.restfulwebservices.restapi.entity;


import com.learn.restfulwebservices.restapi.pojo.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String  destination;
    private int noOfDays;
    @OneToMany
    //@JoinTable(name = "review", joinColumns = @JoinColumn(name = "vacationID"), inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))

    private List<ReviewEntity> reviewList;
}
