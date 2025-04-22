package zoo.infrastructure.repositories;

import org.springframework.stereotype.Component;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;

import java.util.*;

@Component
public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final Map<UUID, Enclosure> enclosures = new HashMap<>();

    @Override
    public void add(Enclosure enclosure) {
        enclosures.put(enclosure.getId(), enclosure);
    }

    @Override
    public void remove(Enclosure enclosure) {
        enclosures.remove(enclosure.getId());
    }

    @Override
    public List<Enclosure> getEnclosures() {
        return new ArrayList<>(enclosures.values());
    }

    @Override
    public Optional<Enclosure> getEnclosureById(UUID id) {
        return Optional.ofNullable(enclosures.get(id));
    }
}
