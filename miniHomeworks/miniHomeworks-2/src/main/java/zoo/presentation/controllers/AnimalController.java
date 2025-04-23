package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.AnimalRepository;
import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.converters.AnimalConverter;
import zoo.infrastructure.dto.AnimalDTO;
import zoo.infrastructure.dto.requests.CreateAnimalRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalRepository animalRepository;

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.getAnimals().stream()
                .map(AnimalConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public AnimalDTO getAnimalByName(@PathVariable String name) {
        Animal animal = animalRepository.getAnimalByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        return AnimalConverter.toDTO(animal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDTO createAnimal(@Valid @RequestBody CreateAnimalRequest createAnimalRequest) {
        var newAnimal = AnimalConverter.toEntity(createAnimalRequest);
        animalRepository.add(newAnimal);
        return AnimalConverter.toDTO(newAnimal);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable String name) {
        var animal = animalRepository.getAnimalByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        animalRepository.remove(animal);
    }
}
