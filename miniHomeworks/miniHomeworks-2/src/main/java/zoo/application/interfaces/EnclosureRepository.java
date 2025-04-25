package zoo.application.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import zoo.domains.entities.Enclosure;

/**
 * interface of enclosure repository.
 */
public interface EnclosureRepository {
    void add(Enclosure enclosure);

    void remove(Enclosure enclosure);

    List<Enclosure> getEnclosures();

    Optional<Enclosure> getEnclosureById(UUID id);

    public void clear();
}
