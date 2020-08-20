package com.learn.restfulwebservices.restapi.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class HotelBookingRequest {
    private String hotelName;
    private String roomType;
    private int userId;
    private Date bookingDate;

}
