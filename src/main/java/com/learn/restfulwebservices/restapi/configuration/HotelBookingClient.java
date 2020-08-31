package com.learn.restfulwebservices.restapi.configuration;

import com.learn.restfulwebservices.restapi.pojo.HotelBookingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "HOTEL-BOOKING-SERVICE")
public interface HotelBookingClient {

    @RequestMapping(method = RequestMethod.POST, value = "/hotel/booking", produces = "application/json")
    public ResponseEntity bookHotel(@RequestBody HotelBookingRequest  hotelBookingRequest);
}

