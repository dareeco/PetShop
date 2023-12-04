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
public class DogDto extends CatDto {
    Integer rating;

    public DogDto(Long Id, String name, PetType type, String description, LocalDate dateOfBirth, Integer price, Integer rating) {
        super(Id, name, type, description, dateOfBirth, price);
        this.rating = rating;
    }
}
