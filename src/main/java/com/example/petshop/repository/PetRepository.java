package com.example.petshop.repository;

import com.example.petshop.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {
}
