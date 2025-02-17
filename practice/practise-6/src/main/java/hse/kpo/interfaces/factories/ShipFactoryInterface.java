package hse.kpo.interfaces;

import hse.kpo.domains.Ship;

/**
 * interface of ship factory.
 */
public interface ShipFactoryInterface<ParamsT> {
    Ship createShip(ParamsT shipParams, int shipNumber);
}
