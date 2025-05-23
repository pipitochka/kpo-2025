package hse.kpo.factories.car;

import hse.kpo.domains.engines.PedalEngine;
import hse.kpo.domains.objects.Car;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * class of pedal car facroty.
 */
@Component
public class PedalCarFactory implements CarFactoryInterface<PedalEngineParams> {

    /**
     * Создает фабрику педальных автомобилей.
     *
     * @param carParams params which will be used in car constructor.
     * @param carNumber unique number of a car.
     */
    @Override
    public Car createCar(PedalEngineParams carParams, int carNumber) {
        var engine = new PedalEngine(carParams.pedalSize()); // создаем
        // двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с установленным двигателем
        // и определенным номером
    }
}
