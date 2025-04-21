package zoo.infrastructure.repositories;

import zoo.application.interfaces.AnimalRepository;
import zoo.domains.entities.Animal;

import java.util.*;

public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<UUID, Animal> animals = new HashMap<>();

    @Override
    public void add(Animal animal) {
        animals.put(animal.getAnimalId(), animal);
    }

    @Override
    public void remove(Animal animal) {
        animals.remove(animal.getAnimalId());
    }

    @Override
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public Optional<Animal> getAnimalById(UUID id) {
        return Optional.ofNullable(animals.get(id));
    }

    @Override
    public Optional<Animal> getAnimalByName(String name) {
        return animals.values().stream()  // Ищем животное по имени
                .filter(animal -> animal.getName().equals(name))
                .findFirst();
    }
}
