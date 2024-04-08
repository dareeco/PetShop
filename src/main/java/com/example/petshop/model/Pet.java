package com.example.petshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    User owner;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    PetType type;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    LocalDate dateOfBirth;
    @Column(nullable = false)
    Integer price;
    @Column()
    Integer rating;

    @OneToOne
    @Column()
    Image imageData;

    public Pet(String name, PetType type, String description, LocalDate dateOfBirth, Integer price, Integer rating) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.rating = rating;
    }

    public Pet(String name, PetType type, String description, LocalDate dateOfBirth, Integer price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
    }

    //Will delete those later, add them to the previous 2 constructors,
    //Dog
    public Pet(User owner, String name, PetType type, String description, LocalDate dateOfBirth, Integer price, Integer rating, Image imageData) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.rating = rating;
        this.imageData = imageData;
    }
    //Cat


    public Pet(User owner, String name, PetType type, String description, LocalDate dateOfBirth, Integer price, Image imageData) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.price = price;
        this.imageData = imageData;
    }
}
