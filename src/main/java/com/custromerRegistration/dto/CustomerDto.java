package com.custromerRegistration.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

   @Size(min=2, message = "name should not be less than two character")
    private String name;

    @Email(message = "invalid email format")
    private String email;

    @Size(min=10, message = "not less two 10 digits")
    private String mobile;


    private String city;
    private String message;

}