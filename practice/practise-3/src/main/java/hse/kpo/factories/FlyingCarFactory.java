package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyEngine;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;

public class FlyingCarFactory implements ICarFactory<EmptyEngineParams> {

    /**
     * Создает фабрику летающих бибик
     * @param carParams
     * @param carNumber
     * @return
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyEngine();

        return new Car(carNumber, engine);
    }
}
