package com.example.petshop.web;


import com.example.petshop.model.Image;
import com.example.petshop.model.Pet;
import com.example.petshop.service.PetService;
import com.example.petshop.web.dto.PetCreationListDto;
import com.example.petshop.web.dto.PetDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping(value = "/edit/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Pet editPicture(@PathVariable Long id, @RequestPart MultipartFile image){
        //because here we only edit the picture, we don't need the petDto otherwise we would have needed it
        //edit(@PathVariable Long id, @RequestPart("pet") PetDto pet, @RequestPart MultipartFile image){
        return this.petService.updatePicture(id,  image);
    }

    @GetMapping(path = "/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable long id) {
        Image image = this.petService.getPetImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(image.getData());
    }
}
