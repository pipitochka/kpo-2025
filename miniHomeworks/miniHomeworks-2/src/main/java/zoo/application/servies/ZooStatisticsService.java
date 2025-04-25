package zoo.application.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.valueobjects.enums.AnimalGender;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of zoo statistic service (to take statistic information about zoo).
 */
@Component
@RequiredArgsConstructor
public class ZooStatisticsService {

    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public int countMaleAnimals() {
        return animalRepository.getAnimals().stream().filter(
                animal -> animal.getGender() == AnimalGender.MALE).toList().size();
    }

    public int countFemaleAnimals() {
        return animalRepository.getAnimals().stream().filter(
                animal -> animal.getGender() == AnimalGender.FEMALE).toList().size();
    }

    public int countAnimalsByType(AnimalTypes type) {
        return animalRepository.getAnimals().stream().filter(
                animal -> animal.getType() == type).toList().size();
    }
}
