package zoo.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.application.servies.ZooStatisticsService;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of zoo statistic controller.
 */
@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class ZooStatisticController {
    private final ZooStatisticsService zooStatisticsService;

    @GetMapping("/count_Male")
    public int getCountMale() {
        return zooStatisticsService.countMaleAnimals();
    }

    @GetMapping("/count_Female")
    public int getCountFemale() {
        return zooStatisticsService.countFemaleAnimals();
    }

    @GetMapping("/count_by_type/{type}")
    public int getCountByType(@PathVariable AnimalTypes type) {
        return zooStatisticsService.countAnimalsByType(type);
    }
}
