package hse.kpo.interfaces;

import hse.kpo.domains.Ship;

public interface ShipFactoryInterface<ParamsT> {
    Ship createShip(ParamsT shipParams, int shipNumber);
}
