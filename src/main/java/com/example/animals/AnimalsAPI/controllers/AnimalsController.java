package com.example.animals.AnimalsAPI.controllers;

import com.example.animals.AnimalsAPI.models.Animal;
import com.example.animals.AnimalsAPI.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalsController {
  @Autowired
  AnimalService animalService;

  @GetMapping("/animals")
  ResponseEntity<List<Animal>> List(){
    return new ResponseEntity<>(animalService.findAll(), HttpStatus.OK);
  }
  @GetMapping("animals/{id}")
  ResponseEntity<Animal> FindAnimal(@PathVariable String id){
    Optional<Animal> animalQuery = animalService.findAnimal(Integer.parseInt(id));
    if(animalQuery.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(animalQuery.get(),HttpStatus.FOUND);
  }

  @PostMapping("/animals")
  ResponseEntity<Animal> NewAnimal(@Valid @RequestBody Animal newAnimal, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return new ResponseEntity<>(newAnimal,HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(animalService.addAnimal(newAnimal),HttpStatus.CREATED);
  }
  @PutMapping("animals/{id}")
  ResponseEntity<Animal> ModifyAnimal(@PathVariable String id, @Valid @RequestBody Animal modifiedAnimal, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return new ResponseEntity<>(modifiedAnimal,HttpStatus.BAD_REQUEST);
    }
    Optional<Animal> animalQuery = animalService.findAnimal(Integer.parseInt(id));
    if(animalQuery.isEmpty()){
      return new ResponseEntity<>(modifiedAnimal,HttpStatus.NOT_FOUND);
    }
    modifiedAnimal.setId(animalQuery.get().getId());
    return new ResponseEntity<>(animalService.modifyAnimal(modifiedAnimal),HttpStatus.OK);
  }
  @DeleteMapping("/animals/{id}")
  ResponseEntity<Animal> DeleteAnimal(@PathVariable String id){
    Optional<Animal> animalQuery = animalService.findAnimal(Integer.parseInt(id));
    if(animalQuery.isEmpty()){
      return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    animalService.deleteAnimal(Integer.parseInt(id));
    return new ResponseEntity<>(animalQuery.get(),HttpStatus.OK);
  }
}
