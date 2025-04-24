package zoo.application.interfaces;

import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnclosureRepository {
    void add(Enclosure enclosure);

    void remove(Enclosure enclosure);

    List<Enclosure> getEnclosures();

    Optional<Enclosure> getEnclosureById(UUID id);

    public void clear();
}
