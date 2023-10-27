package com.example.petshop.service;

import com.example.petshop.model.Pet;
import com.example.petshop.model.PetType;
import com.example.petshop.model.TransactionHistoryLog;
import com.example.petshop.model.User;
import com.example.petshop.repository.PetRepository;
import com.example.petshop.repository.TransactionHistoryLogRepository;
import com.example.petshop.repository.UserRepository;
import com.example.petshop.web.dto.ReceiptDto;
import com.example.petshop.web.dto.UserCreationDto;
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
        user10.forEach(el -> {
            createUser(el);
        });
    }

    public void createUser(UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.getFirstName(), userCreationDto.getLastName(), userCreationDto.getEmail(), userCreationDto.getBudget());
        userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
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
