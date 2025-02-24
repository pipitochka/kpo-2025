package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;

/**
 * interface of ship provider.
 */
public interface ShipProviderInterface {
    Ship takeShip(Customer customer); // Метод возвращает optional на Car, что означает,
    // что метод может ничего не вернуть
}
