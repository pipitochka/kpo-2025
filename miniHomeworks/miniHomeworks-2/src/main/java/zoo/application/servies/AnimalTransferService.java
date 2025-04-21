package zoo.application.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;
import zoo.domains.events.AnimalMovedEvent;
import zoo.domains.events.EventHandler;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnimalTransferService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;
    private final EventHandler eventHandler;

    public void moveAnimal(String animalName, UUID enclosureId) throws Exception {
        var animal = animalRepository.getAnimalByName(animalName)
                .orElseThrow(() -> new Exception("Animal not found"));

        var enclosure = enclosureRepository.getEnclosureById(enclosureId)
                .orElseThrow(() -> new Exception("Enclosure not found"));

        Enclosure oldEnclosure = null;

        if (animal.getEnclosure() != null) {
            oldEnclosure = enclosureRepository.getEnclosureById(animal.getEnclosure().getId())
                    .orElseThrow(() -> new Exception("Previous enclosure not found"));
            oldEnclosure.remove(animal);
        }

        enclosure.add(animal);

        eventHandler.handle(new AnimalMovedEvent(
                animal.getAnimalId(),
                oldEnclosure != null ? oldEnclosure.getId() : null,
                enclosure.getId()
        ));

    }
}
