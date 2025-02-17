package hse.kpo.interfaces;

import hse.kpo.domains.Car;

/**
 * inteface of car factory.
 *
 * @param <ParamsT> params which used to make a car.
 */
public interface CarFactoryInterface<ParamsT> {
    Car createCar(ParamsT carParams, int carNumber);
}
