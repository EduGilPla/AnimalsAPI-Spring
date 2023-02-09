package com.example.animals.AnimalsAPI.repositories;

import com.example.animals.AnimalsAPI.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
}
