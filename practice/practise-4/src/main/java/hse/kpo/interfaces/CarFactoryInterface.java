package hse.kpo.interfaces;

import hse.kpo.domains.Car;

/**
 * Interface of car factory.
 *
 * @param <CarParamsT> params which will be used in car constructor.
 */
public interface CarFactoryInterface<CarParamsT> {
    Car createCar(CarParamsT carParams, int carNumber);
}
