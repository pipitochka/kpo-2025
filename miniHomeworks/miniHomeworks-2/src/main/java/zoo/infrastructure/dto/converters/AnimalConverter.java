package zoo.infrastructure.dto.converters;

import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.AnimalDto;
import zoo.infrastructure.dto.requests.CreateAnimalRequest;

/**
 * class of animal converter(to DTO and to Animal).
 */
public class AnimalConverter {

    /**
     * function to convert animal to dto.
     *
     * @param animal object.
     * @return dto object.
     */
    public static AnimalDto toDto(Animal animal) {
        var enclosureId = animal.getEnclosure() == null ? null : animal.getEnclosure().getId();
        return new AnimalDto(animal.getName(), animal.getType(), animal.getBirthDate(),
                animal.getGender(), animal.getAnimalId(), enclosureId, animal.getFavoriteFood(), animal.getStatus());
    }

    /**
     * function to move requests to animal.
     *
     * @param createAnimalRequest requests with animal description.
     * @return constructed animal
     */
    public static Animal toEntity(CreateAnimalRequest createAnimalRequest) {
        var newAnimal = new Animal(createAnimalRequest.getType(), createAnimalRequest.getName(),
                createAnimalRequest.getBirthDate(), createAnimalRequest.getGender());
        newAnimal.setStatus(createAnimalRequest.getStatus());
        newAnimal.setFavoriteFood(createAnimalRequest.getFavoriteFood());
        return newAnimal;
    }
}
