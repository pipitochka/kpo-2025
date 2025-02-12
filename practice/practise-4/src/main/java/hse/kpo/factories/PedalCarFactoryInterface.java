package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngineInterface;
import hse.kpo.interfaces.CarFactoryInterface;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * class of pedal car facroty.
 */
@Component
public class PedalCarFactoryInterface implements CarFactoryInterface<PedalEngineParams> {

    /**
     * Создает фабрику педальных автомобилей.
     *
     * @param carParams params which will be used in car constructor.
     * @param carNumber unique number of a car.
     */
    @Override
    public Car createCar(PedalEngineParams carParams, int carNumber) {
        var engine = new PedalEngineInterface(carParams.pedalSize()); // создаем
        // двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с установленным двигателем
        // и определенным номером
    }
}
