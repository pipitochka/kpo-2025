package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import java.util.List;

/**
 * interface of ship provider.
 */
public interface ShipProviderInterface {
    Ship takeShip(Customer customer); // Метод возвращает optional на Car, что означает,
    // что метод может ничего не вернуть

    List<Ship> getShips();

    public <ParamsT> Ship addShip(ShipFactoryInterface<ParamsT> shipFactory, ParamsT shipParams);

    public void clear();
}
