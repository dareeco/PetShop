package com.example.petshop.model.Exceptions;

public class InvalidPetException extends RuntimeException{
    public InvalidPetException(){
        super(String.format("Pet is not found"));
    }
}
