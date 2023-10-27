package com.example.petshop.web.dto;

import com.example.petshop.model.PetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetCreationDto {
    @NotBlank(message = "Name must not be blank")
    String name;
    @NotBlank(message = "Type must not be blank")
    PetType type;
    @NotBlank(message = "Description must not be blank")
    String description;
    @NotBlank(message = "Date of Birth must not be blank")
    LocalDate dateOfBirth;
    Integer rating;
}
