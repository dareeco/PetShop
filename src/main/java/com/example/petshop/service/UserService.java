package com.example.petshop.service;

import com.example.petshop.model.Pet;
import com.example.petshop.model.PetType;
import com.example.petshop.model.TransactionHistoryLog;
import com.example.petshop.model.User;
import com.example.petshop.repository.PetRepository;
import com.example.petshop.repository.TransactionHistoryLogRepository;
import com.example.petshop.repository.UserRepository;
import com.example.petshop.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TransactionHistoryLogRepository transactionHistoryLogRepository;
    private final PetRepository petRepository;


    public void createUsers(List<UserCreationDto> users) {
        int numberOfUsersAllowed = 10 - userRepository.findAll().size();
        List<UserCreationDto> user10 = users.subList(0, Math.min(users.size(), numberOfUsersAllowed));
        user10.forEach(this::createUser);
    }

    public void createUser(UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.getFirstName(), userCreationDto.getLastName(), userCreationDto.getEmail(), userCreationDto.getBudget());
        userRepository.save(user);
    }

    public List<UserDto> listAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBudget(),
                        user.getPets().stream().filter(pet -> pet.getType().equals(PetType.DOG))
                                .map(dog -> new DogDto(dog.getId(), dog.getName(), dog.getType(), dog.getDescription(), dog.getDateOfBirth(), dog.getPrice(), dog.getRating())).toList(),
                        user.getPets().stream().filter(pet -> pet.getType().equals(PetType.CAT))
                                .map(cat -> new CatDto(cat.getId(), cat.getName(), cat.getType(), cat.getDescription(), cat.getDateOfBirth(), cat.getPrice())).toList())).toList();

    }

    public ReceiptDto buyPets() {
        List<User> users = userRepository.findAll();
        List<Pet> pets = petRepository.findAll();
        List<String> receiptLines = new ArrayList<>();
        Integer successful = 0;
        Integer unsuccesful = 0;
        boolean boughtAPet;

        for (User user : users) {
            boughtAPet = false;
            for (Pet pet : pets) {
                if (user.getBudget() >= pet.getPrice() && pet.getOwner() == null) {
                    user.setBudget(user.getBudget() - pet.getPrice());
                    pet.setOwner(user);
                    boughtAPet = true;
                    successful++;

                    userRepository.save(user);
                    petRepository.save(pet);
                    receiptLines.add(buyMessage(pet, user));

                    break;
                }

            }
            if (!boughtAPet) {
                unsuccesful++;
            }
        }
        transactionHistoryLogRepository.save(new TransactionHistoryLog(LocalDate.now(), successful, unsuccesful));
        return new ReceiptDto(receiptLines);
    }

    private String buyPetText(String petName, String userFirstName, String userLastName) {
        return petName + " has owner " + userFirstName + " " + userLastName;
    }

    private String buyMessage(Pet pet, User user) {
        if (pet.getType().equals(PetType.CAT)) {
            return "Meow, cat " + buyPetText(pet.getName(), user.getFirstName(), user.getLastName());
        } else {
            return "Woof, dog " + buyPetText(pet.getName(), user.getFirstName(), user.getLastName());
        }
    }

}
