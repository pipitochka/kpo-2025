package zoo.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.application.servies.FeedingOrganizationService;

/**
 * controller of feeding organization.
 */
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
