package com.example.petshop.service;


import com.example.petshop.model.Pet;
import com.example.petshop.model.PetType;
import com.example.petshop.repository.PetRepository;
import com.example.petshop.web.dto.PetCreationDto;
import com.example.petshop.web.dto.PetDto;
import com.example.petshop.web.dto.UserReadingDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public void createPets(List<PetCreationDto> petCreationDtoList) {
        int numberOfAllowedPets = 20 - petRepository.findAll().size();
        List<PetCreationDto> pet20 = petCreationDtoList.subList(0, Math.min(petCreationDtoList.size(), numberOfAllowedPets));
        pet20.forEach(this::createPet);
    }

    public void createPet(PetCreationDto petCreationDto) {
        Pet pet;
        if (petCreationDto.getType().equals(PetType.CAT)) {
            Integer fullPrice = calculatePrice(petCreationDto.getDateOfBirth());
            pet = new Pet(petCreationDto.getName(), petCreationDto.getType(), petCreationDto.getDescription(), petCreationDto.getDateOfBirth(), fullPrice);
        } else {
            Integer fullPrice = calculateDogPrice(petCreationDto.getDateOfBirth(), petCreationDto.getRating());
            pet = new Pet(petCreationDto.getName(), petCreationDto.getType(), petCreationDto.getDescription(), petCreationDto.getDateOfBirth(), fullPrice, petCreationDto.getRating());

        }
        petRepository.save(pet);
    }

    public Integer calculatePrice(LocalDate dateOf) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOf, currentDate);
        return period.getYears();
    }

    public Integer calculateDogPrice(LocalDate dateOf, Integer rating) {
        return calculatePrice(dateOf) + rating;
    }

    public List<PetDto> listAll() {
        return petRepository.findAll().stream()
                .map(pet ->
                        new PetDto(pet.getId(), pet.getName(), pet.getType(), pet.getDescription(),
                                pet.getDateOfBirth(), pet.getPrice(), pet.getRating(),
                                pet.getOwner() != null ? new UserReadingDto(pet.getOwner().getId(), pet.getOwner().getFirstName(),
                                        pet.getOwner().getLastName(), pet.getOwner().getEmail(),
                                        pet.getOwner().getBudget()) : null)).toList();

    }

    public List<PetDto> listAllPageable(PageRequest pr){
        return petRepository.findAll(pr).stream().map( pet ->  new PetDto(pet.getId(), pet.getName(), pet.getType(), pet.getDescription(), pet.getDateOfBirth(), pet.getPrice(), pet.getRating(),
                pet.getOwner() != null ? new UserReadingDto(pet.getOwner().getId(), pet.getOwner().getFirstName(), pet.getOwner().getLastName(), pet.getOwner().getEmail(),
                        pet.getOwner().getBudget()) :null)).toList();
    }

    public Integer listLength(){
        return petRepository.findAll().size();
    }

    public PetDto updatePicture(Long Id, PetDto petDtoParam, MultipartFile image){
//        Pet pet1=this.petRepository.findById(Id).orElseThrow(InvalidPetException::new);
//        PetDto petDto=mapPetToDto(pet);

        return null;
    }

}
