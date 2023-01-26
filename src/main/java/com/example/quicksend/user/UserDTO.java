package com.example.quicksend.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO{
    private Long id;

    private Integer age;
    private String firstName, lastName, email, password, phoneNumber, address, city, country, postalCode;
    private LocalDate dob;
}
