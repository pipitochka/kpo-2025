package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;

/**
 * interface of car provider.
 */
public interface CarProviderInterface {
    Car takeCar(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}
