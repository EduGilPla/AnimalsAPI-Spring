package com.example.animals.AnimalsAPI.services;

import com.example.animals.AnimalsAPI.models.Animal;
import com.example.animals.AnimalsAPI.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
  @Autowired
  AnimalRepository animalRepository;

  public List<Animal> findAll(){
    return animalRepository.findAll();
  }
  public Optional<Animal> findAnimal(int id){
    return animalRepository.findById(id);
  }
  public Animal addAnimal(Animal newAnimal){
    return animalRepository.save(newAnimal);
  }
  public Animal modifyAnimal(Animal modifiedAnimal){
    return animalRepository.save(modifiedAnimal);
  }
  public void deleteAnimal(int id){
    animalRepository.deleteById(id);
  }
}
