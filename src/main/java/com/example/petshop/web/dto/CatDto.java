package com.example.petshop.web.dto;

import com.example.petshop.model.PetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatDto {
    Long Id;

    String name;

    PetType type;

    String description;

    LocalDate dateOfBirth;

    Integer price;
}
