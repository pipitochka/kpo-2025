package zoo.application.interfaces;

import zoo.domains.entities.Animal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AnimalRepository {
    void add(Animal animal);

    void remove(Animal animal);

    List<Animal> getAnimals();

    Optional<Animal> getAnimalById(UUID id);

    Optional<Animal> getAnimalByName(String name);

    public void clear();
}
