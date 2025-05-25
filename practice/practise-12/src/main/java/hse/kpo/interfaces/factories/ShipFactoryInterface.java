package hse.kpo.interfaces.factories;

import hse.kpo.domains.objects.Ship;

/**
 * interface of ship factory.
 */
public interface ShipFactoryInterface<ParamsT> {
    Ship create(ParamsT shipParams);
}
