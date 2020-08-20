package com.learn.restfulwebservices.restapi.service;

import com.learn.restfulwebservices.restapi.pojo.HotelBookingRequest;
import com.learn.restfulwebservices.restapi.pojo.VacationBookingReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class VacationBookingService {
    @Autowired
    private RestTemplate restTemplate;
    private String HOTEL_SERVICE_URL="http://10.101.160.217:8091/hotel/booking";
    //To-DO  Exception Handling
    public ResponseEntity book(long vacationID, VacationBookingReq vacationBookingReq){
        HotelBookingRequest hotelBookingRequest= new HotelBookingRequest();
        hotelBookingRequest.setBookingDate(new Date());
        hotelBookingRequest.setHotelName("BBQ");
        hotelBookingRequest.setRoomType("BUSINESS");
        hotelBookingRequest.setUserId(3);
        ResponseEntity response= restTemplate.postForEntity(HOTEL_SERVICE_URL,hotelBookingRequest,HotelBookingRequest.class);
        if(response.getStatusCode()== HttpStatus.CREATED){
           return  ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return  ResponseEntity.ok(" fail to book");
        }
    }
}
