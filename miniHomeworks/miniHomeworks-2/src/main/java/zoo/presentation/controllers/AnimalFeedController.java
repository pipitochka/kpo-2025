package zoo.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.application.servies.FeedingOrganizationService;
import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.FeedingSchedulesDTO;
import zoo.infrastructure.dto.converters.FeedingSchedulesConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feeding")
@RequiredArgsConstructor
public class AnimalFeedController {
    private final FeedingOrganizationService feedingOrganizationService;

    @PostMapping("/feed")
    public void feedAnimals() {
        feedingOrganizationService.feed();
    }
}
