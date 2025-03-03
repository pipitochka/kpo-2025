package hse.kpo.interfaces.factories;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Catamaran;
import hse.kpo.domains.objects.Ship;

public interface CatamaranFactoryInterface<ParamsT> {
    Car createCatamaran(ParamsT carParams, int carNumber);
}
