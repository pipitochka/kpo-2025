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
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.infrastructure.dto.FeedingSchedulesDto;
import zoo.infrastructure.dto.converters.FeedingSchedulesConverter;
import zoo.infrastructure.dto.requests.CreateFeedingScheduleRequest;

/**
 * class of feeding schedule controller.
 */
@RestController
@RequestMapping("/feedingSchedule")
@RequiredArgsConstructor
public class FeedingScheduleController {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;

    /**
     * function to take all feeding schedules.
     *
     * @return list of dto feeding schedules objects.
     */
    @GetMapping("/feedingSchedules")
    public List<FeedingSchedulesDto> getFeedingSchedules() {
        return feedingScheduleRepository.getSchedules().stream()
                .map(FeedingSchedulesConverter::toDto).collect(Collectors.toList());
    }

    /**
     * function to create feeding schedule from requests.
     *
     * @param createFeedingScheduleRequest description of feeding schedule.
     * @return dto object of feeding schedule.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedingSchedulesDto createFeedingSchedule(@Valid @RequestBody
                                                         CreateFeedingScheduleRequest createFeedingScheduleRequest) {
        var animal = animalRepository.getAnimalByName(createFeedingScheduleRequest.getName());
        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
        }
        var newFeedingSchedule = FeedingSchedulesConverter.toEntity(createFeedingScheduleRequest,
                animal.get().getAnimalId());
        feedingScheduleRepository.add(newFeedingSchedule);
        return FeedingSchedulesConverter.toDto(newFeedingSchedule);
    }

    /**
     * function to delete feeding schedule by id.
     *
     * @param id of feeding schedule.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedingSchedule(@PathVariable UUID id) {
        var feedingSchedule = feedingScheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal not found"));
        feedingScheduleRepository.remove(feedingSchedule);
    }

}
