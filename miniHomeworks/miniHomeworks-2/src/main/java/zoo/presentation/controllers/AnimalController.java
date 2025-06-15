package zoo.presentation.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.AnimalRepository;
import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.AnimalDto;
import zoo.infrastructure.dto.converters.AnimalConverter;
import zoo.infrastructure.dto.requests.CreateAnimalRequest;


/**
 * class of animal controller.
 */
@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalRepository animalRepository;

    /**
     * function to get all animals.
     *
     * @return list of dto animals.
     */
    @GetMapping
    public List<AnimalDto> getAllAnimals() {
        return animalRepository.getAnimals().stream()
                .map(AnimalConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * function to get information about animal by name.
     *
     * @param name of animal.
     * @return dto of animal
     */
    @GetMapping("/{name}")
    public AnimalDto getAnimalByName(@PathVariable String name) {
        Animal animal = animalRepository.getAnimalByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        return AnimalConverter.toDto(animal);
    }

    /**
     * function to create animal from requests.
     *
     * @param createAnimalRequest description of animal.
     * @return animal dto object.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDto createAnimal(@Valid @RequestBody CreateAnimalRequest createAnimalRequest) {
        var newAnimal = AnimalConverter.toEntity(createAnimalRequest);
        animalRepository.add(newAnimal);
        return AnimalConverter.toDto(newAnimal);
    }

    /**
     * function to delete animal by name.
     *
     * @param name - name of animal.
     */
    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable String name) {
        var animal = animalRepository.getAnimalByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        animalRepository.remove(animal);
    }
}
