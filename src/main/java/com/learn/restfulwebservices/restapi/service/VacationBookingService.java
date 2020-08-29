package com.learn.restfulwebservices.restapi.service;

import com.learn.restfulwebservices.restapi.communication.HotelBookingServiceProxy;
import com.learn.restfulwebservices.restapi.pojo.HotelBookingRequest;
import com.learn.restfulwebservices.restapi.pojo.VacationBookingReq;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @Autowired
    private HotelBookingServiceProxy proxy;

    private String HOTEL_SERVICE_URL = "http://localhost:8091/hotel/booking";

    public ResponseEntity bookRestTemplate(long vacationID, VacationBookingReq vacationBookingReq) {
        HotelBookingRequest hotelBookingRequest = getBookingRequest();
        ResponseEntity response = restTemplate.postForEntity(HOTEL_SERVICE_URL, hotelBookingRequest, HotelBookingRequest.class);
        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.ok(" fail to book");
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackBook")
    public ResponseEntity bookFeign(long vacationID, VacationBookingReq vacationBookingReq) {
        HotelBookingRequest hotelBookingRequest = getBookingRequest();
        ResponseEntity response = proxy.bookHotel(hotelBookingRequest);
        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.ok(" fail to book");
        }
    }

    public ResponseEntity fallbackBook(long vacationID, VacationBookingReq vacationBookingReq) {
        return ResponseEntity.ok("While we book your vacation, you can please browse other vacations");
    }

    private HotelBookingRequest getBookingRequest() {
        HotelBookingRequest hotelBookingRequest = new HotelBookingRequest();
        hotelBookingRequest.setBookingDate(new Date());
        hotelBookingRequest.setHotelName("BBQ");
        hotelBookingRequest.setRoomType("BUSINESS");
        hotelBookingRequest.setUserId(3);
        return hotelBookingRequest;
    }

}
