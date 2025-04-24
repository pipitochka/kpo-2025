package zoo.presentation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zoo.application.servies.ZooStatisticsService;
import zoo.domains.valueObjects.enums.AnimalTypes;
import zoo.infrastructure.dto.requests.MoveAnimalRequest;
import zoo.infrastructure.dto.requests.StatisticRequest;

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
