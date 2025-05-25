package zoo.domains;

import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.interfaces.InterfaceVetClinic;

/**
 * realizations of interface of InterfaceVetClinic which check animal will they soon die.
 */
@Component
public class MyVetClinic implements InterfaceVetClinic {

    /** function which return if animal healthy.
     *
     * @return true or false
     */
    @Override
    public boolean isHealthy(Animal animal) {
        return true;
    }
}
