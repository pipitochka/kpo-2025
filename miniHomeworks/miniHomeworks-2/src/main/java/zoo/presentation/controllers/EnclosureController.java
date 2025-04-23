package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;
import zoo.infrastructure.dto.AnimalDTO;
import zoo.infrastructure.dto.EnclosureDTO;
import zoo.infrastructure.dto.converters.AnimalConverter;
import zoo.infrastructure.dto.converters.EnclosureConverter;
import zoo.infrastructure.dto.requests.CreateAnimalRequest;
import zoo.infrastructure.dto.requests.CreateEnclosureRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enclosures")
@RequiredArgsConstructor
public class EnclosureController {

    private final EnclosureRepository enclosureRepository;

    @GetMapping
    public List<EnclosureDTO> getEnclosures() {
        return enclosureRepository.getEnclosures().stream()
                .map(EnclosureConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EnclosureDTO getAnimalByName(@PathVariable UUID id) {
        Enclosure enclosure = enclosureRepository.getEnclosureById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        return EnclosureConverter.toDTO(enclosure);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnclosureDTO createEnclosure(@Valid @RequestBody CreateEnclosureRequest createEnclosureRequest) {
        var newEnclosure = EnclosureConverter.toEntity(createEnclosureRequest);
        enclosureRepository.add(newEnclosure);
        return EnclosureConverter.toDTO(newEnclosure);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable UUID id) {
        var enclosure = enclosureRepository.getEnclosureById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        enclosureRepository.remove(enclosure);
    }

}
