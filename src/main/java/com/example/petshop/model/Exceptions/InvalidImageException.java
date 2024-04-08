package com.example.petshop.model.Exceptions;

public class InvalidImageException extends RuntimeException{
    public InvalidImageException(String name){
        super(String.format("Image " + name +" is not found"));
    }
}
