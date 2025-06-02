package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import java.util.List;

/**
 * interface of car provider.
 */
public interface CarProviderInterface {
    Car takeCar(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть

    public List<Car> getCars();

    public <T> Car addCar(CarFactoryInterface<T> carFactory, T carParams);

    public void clear();
}
