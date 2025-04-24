package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;
import zoo.application.servies.AnimalTransferService;
import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.requests.MoveAnimalRequest;

@RestController
@RequestMapping("/movement")
@RequiredArgsConstructor
public class AnimalMovementController {

    private final AnimalTransferService animalTransferService;

    @PostMapping("/animals/move")
    @ResponseStatus(HttpStatus.OK)
    public void moveAnimal(@Valid @RequestBody MoveAnimalRequest request) {
        try{
            animalTransferService.moveAnimal(request.getName(), request.getEnclosureId());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
