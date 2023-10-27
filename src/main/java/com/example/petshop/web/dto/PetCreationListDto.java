package com.example.petshop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetCreationListDto {
    @NotEmpty(message = "List of users must be provided")
    List<PetCreationDto> petCreationDtos;
}
