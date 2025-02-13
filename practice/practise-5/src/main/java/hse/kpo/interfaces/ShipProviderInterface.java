package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Ship;

public interface ShipProviderInterface {
    Ship takeShip(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}
