package hse.kpo.interfaces.factories;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Catamaran;
import hse.kpo.domains.objects.Ship;

/**
 * interface of catamaran factory.
 *
 * @param <ParamsT> params which used to make a car.
 */
public interface CatamaranFactoryInterface<ParamsT> {
    Car createCatamaran(ParamsT carParams, int carNumber);
}
