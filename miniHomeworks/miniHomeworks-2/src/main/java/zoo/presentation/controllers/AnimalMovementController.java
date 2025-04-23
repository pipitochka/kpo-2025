package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.requests.MoveAnimalRequest;

@RestController
@RequestMapping("/movement")
@RequiredArgsConstructor
public class AnimalMovementController {

    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    @PostMapping("/animals/move")
    @ResponseStatus(HttpStatus.OK)
    public void moveAnimal(@Valid @RequestBody MoveAnimalRequest request) {
        var animal = animalRepository.getAnimalByName(request.getName());
        var enclosure = enclosureRepository.getEnclosureById(request.getEnclosureId());
        if (animal == null || enclosure == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal or Enclosure not found");
        }
        try {
            animal.get().
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
