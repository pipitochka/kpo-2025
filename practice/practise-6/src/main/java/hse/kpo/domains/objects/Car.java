package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.Getter;
import lombok.ToString;

/**
 * class to makes cars.
 */
@ToString
public class Car {

    private EngineInterface engine;

    @Getter
    private int vin;

    /**
     * Создает автомобиль.
     *
     * @param vin unique number of a car.
     * @param engine engine witch will be used for a car.
     */
    public Car(int vin, EngineInterface engine) {
        this.vin = vin;
        this.engine = engine;
    }

    /**
     * Проверяет подходит ли человек машине.
     *
     * @param customer customer who would buy a car.
     * @return true если подходят, false - иначе.
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CAR); // внутри метода
        // просто вызываем соответствующий метод двигателя
    }
}
