package com.example.petshop.web;

import com.example.petshop.service.UserService;
import com.example.petshop.web.dto.ReceiptDto;
import com.example.petshop.web.dto.UserDto;
import com.example.petshop.web.dto.UserOwnerDto;
import com.example.petshop.web.dto.UsersCreationListDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
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
    public List<UserDto> listAll() {
        return this.userService.listAll();
    }

    @GetMapping("/pageable")
    public List<UserDto> listAllPageable(@RequestParam int page, @RequestParam int size){
        PageRequest pr= PageRequest.of(page,size);
        return this.userService.listAllPageable(pr);
    }

    @GetMapping("/owners")
    public List<UserOwnerDto> listAllOwners(@RequestParam int page, @RequestParam int size){
        PageRequest pr= PageRequest.of(page,size);
        return this.userService.getUsersOwningPets(pr);
    }

    @GetMapping("/length")
    public Integer getLength(){ return userService.listLength();}
}
