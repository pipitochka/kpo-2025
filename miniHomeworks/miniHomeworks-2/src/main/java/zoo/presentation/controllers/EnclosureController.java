package zoo.presentation.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
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
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Enclosure;
import zoo.infrastructure.dto.EnclosureDto;
import zoo.infrastructure.dto.converters.EnclosureConverter;
import zoo.infrastructure.dto.requests.CreateEnclosureRequest;


/**
 * class of enclosure controller.
 */
@RestController
@RequestMapping("/enclosures")
@RequiredArgsConstructor
public class EnclosureController {

    private final EnclosureRepository enclosureRepository;

    /**
     * function to get all enclosures.
     *
     * @return list of enclosures dto objects.
     */
    @GetMapping
    public List<EnclosureDto> getEnclosures() {
        return enclosureRepository.getEnclosures().stream()
                .map(EnclosureConverter::toDto).collect(Collectors.toList());
    }

    /**
     * function to get enclosure by id.
     *
     * @param id of enclosure.
     * @return dto of enclosure.
     */
    @GetMapping("/{id}")
    public EnclosureDto getAnimalByName(@PathVariable UUID id) {
        Enclosure enclosure = enclosureRepository.getEnclosureById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        return EnclosureConverter.toDto(enclosure);
    }

    /**
     * function to create enclosure from request.
     *
     * @param createEnclosureRequest description.
     * @return dto object of enclosure.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnclosureDto createEnclosure(@Valid @RequestBody CreateEnclosureRequest createEnclosureRequest) {
        var newEnclosure = EnclosureConverter.toEntity(createEnclosureRequest);
        enclosureRepository.add(newEnclosure);
        return EnclosureConverter.toDto(newEnclosure);
    }

    /**
     * function to delete enclosure by id.
     *
     * @param id of enclosure.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable UUID id) {
        var enclosure = enclosureRepository.getEnclosureById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        enclosureRepository.remove(enclosure);
    }

}
