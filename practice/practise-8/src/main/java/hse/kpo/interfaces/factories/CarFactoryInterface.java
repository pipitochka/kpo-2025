package hse.kpo.interfaces.factories;

import hse.kpo.domains.objects.Car;

/**
 * inteface of car factory.
 *
 * @param <ParamsT> params which used to make a car.
 */
public interface CarFactoryInterface<ParamsT> {
    Car createCar(ParamsT carParams, int carNumber);
}
