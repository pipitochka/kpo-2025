package zoo.infrastructure.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Enclosure;

/**
 * realization of enclosure repository in memory.
 */
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

    @Override
    public void clear() {
        enclosures.clear();
    }
}
