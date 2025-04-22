package zoo.infrastructure.dto.converters;

import zoo.domains.entities.Animal;
import zoo.infrastructure.dto.AnimalDTO;
import zoo.infrastructure.dto.requests.CreateAnimalRequest;

public class AnimalConverter {
    public static AnimalDTO toDTO(Animal animal) {
        var enclosureId = animal.getEnclosure() == null ? null : animal.getEnclosure().getId();
        return new AnimalDTO(animal.getName(), animal.getType(), animal.getBirthDate(),
                animal.getGender(), animal.getAnimalId(),  enclosureId, animal.getFavoriteFood(), animal.getStatus());
    }

    public static Animal toEntity(CreateAnimalRequest createAnimalRequest) {
        var newAnimal = new Animal(createAnimalRequest.getType(), createAnimalRequest.getName(),
                createAnimalRequest.getBirthDate(), createAnimalRequest.getGender());
        newAnimal.setStatus(createAnimalRequest.getStatus());
        newAnimal.setFavoriteFood(createAnimalRequest.getFavoriteFood());
        return newAnimal;
    }
}
