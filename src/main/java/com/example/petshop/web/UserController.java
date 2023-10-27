package com.example.petshop.web;

import com.example.petshop.model.User;
import com.example.petshop.service.UserService;
import com.example.petshop.web.dto.ReceiptDto;
import com.example.petshop.web.dto.UsersCreationListDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UsersCreationListDto usersCreationDto) {
        this.userService.createUsers(usersCreationDto.getUserCreationDtos());
    }

    @GetMapping("/buy")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReceiptDto buyPets() {
        return this.userService.buyPets();
    }

    @GetMapping
    public List<User> listAll() {
        return this.userService.listAll();
    }
}
