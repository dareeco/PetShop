package com.example.petshop.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    Long Id;

    String firstName;

    String lastName;

    String email;

    Float budget;
    private List<DogDto> dogs;
    private List<CatDto> catDtos;


}
