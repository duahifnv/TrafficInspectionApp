package dstu.trafficinspection.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DriverDto {
    private String fullName;
    private Date dateOfBirth;
    private String registrationAddress;
}
