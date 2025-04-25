package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.servies.AnimalTransferService;
import zoo.infrastructure.dto.requests.MoveAnimalRequest;

/**
 * class of animal movement controller.
 */
@RestController
@RequestMapping("/movement")
@RequiredArgsConstructor
public class AnimalMovementController {

    private final AnimalTransferService animalTransferService;

    /**
     * function to move animal.
     *
     * @param request description of movement.
     */
    @PostMapping("/animals/move")
    @ResponseStatus(HttpStatus.OK)
    public void moveAnimal(@Valid @RequestBody MoveAnimalRequest request) {
        try {
            animalTransferService.moveAnimal(request.getName(), request.getEnclosureId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
