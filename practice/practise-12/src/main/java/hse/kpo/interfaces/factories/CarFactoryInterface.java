package hse.kpo.interfaces.factories;

import hse.kpo.domains.objects.Car;

/**
 * inteface of car factory.
 *
 * @param <T> params which used to make a car.
 */
public interface CarFactoryInterface<T> {
    Car create(T parameters);
}
