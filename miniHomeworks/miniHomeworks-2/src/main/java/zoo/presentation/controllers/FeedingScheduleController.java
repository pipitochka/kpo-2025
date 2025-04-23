package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.Enclosure;
import zoo.domains.entities.FeedingSchedule;
import zoo.infrastructure.dto.EnclosureDTO;
import zoo.infrastructure.dto.FeedingSchedulesDTO;
import zoo.infrastructure.dto.converters.FeedingSchedulesConverter;
import zoo.infrastructure.dto.requests.CreateEnclosureRequest;
import zoo.infrastructure.dto.requests.CreateFeedingScheduleRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedingSchedule")
@RequiredArgsConstructor
public class FeedingScheduleController {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;

    @GetMapping
    public List<FeedingSchedulesDTO> getFeedingSchedules() {
        return feedingScheduleRepository.getSchedules().stream()
                .map(FeedingSchedulesConverter::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedingSchedulesDTO createFeedingSchedule(@Valid @RequestBody CreateFeedingScheduleRequest createFeedingScheduleRequest) {
        var animal = animalRepository.getAnimalByName(createFeedingScheduleRequest.getName());
        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
        }
        var newFeedingSchedule = FeedingSchedulesConverter.toEntity(createFeedingScheduleRequest,
                animal.get().getAnimalId());
        feedingScheduleRepository.add(newFeedingSchedule);
        return FeedingSchedulesConverter.toDTO(newFeedingSchedule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedingSchedule(@PathVariable UUID id) {
        var feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        feedingScheduleRepository.remove(feedingSchedule);
    }

}
