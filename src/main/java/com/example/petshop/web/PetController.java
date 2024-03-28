package com.example.petshop.web;



import com.example.petshop.service.PetService;
import com.example.petshop.web.dto.PetCreationListDto;
import com.example.petshop.web.dto.PetDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class PetController {
    private final PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PetCreationListDto petCreationListDto) {
        this.petService.createPets(petCreationListDto.getPetCreationDtos());
    }

    @GetMapping
    public List<PetDto> listAll() {
        return this.petService.listAll();
    }

    @GetMapping("/pageable")
    public List<PetDto> findAll(@RequestParam int page, @RequestParam int size){
        // if this doesn't work try making it public Page<PetDto> findAll, and in the service as well
        PageRequest pr= PageRequest.of(page,size);
        return petService.listAllPageable(pr);
    }

    @GetMapping("/length")
    public Integer getLength(){
        return petService.listLength();
    }
}
