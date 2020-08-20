package com.learn.restfulwebservices.restapi.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VacationBookingReq {
    private long vactionID;
    private String packageName;
}
