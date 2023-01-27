package com.example.quicksend.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO{
    @JsonProperty("id")
    private long id;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String  lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("dob")
    private LocalDate dob;
}
