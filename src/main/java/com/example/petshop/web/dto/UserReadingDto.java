package com.example.petshop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadingDto {
    Long Id;

    String firstName;

    String lastName;

    String email;

    Float budget;
}
