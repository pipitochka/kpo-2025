package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс описывающий машину.
 */
@ToString
public class Car {

    private EngineInterface engine;

    @Getter
    private int vin;

    /**
     * Создает автомобиль.
     *
     * @param vin уникальный индефикатор автомобиля.
     * @param engine двигатель автомобиля.
     */
    public Car(int vin, EngineInterface engine) {
        this.vin = vin;
        this.engine = engine;
    }

    /**
     * Проверяет подходит ли человек машине.
     *
     * @param customer покупатель
     * @return true если подходят, false - иначе
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
