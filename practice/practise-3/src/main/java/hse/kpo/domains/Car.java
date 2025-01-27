package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    /**
     * Создает автомобиль
     * @param VIN
     * @param engine
     */
    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     * Проверяет подходит ли человек машине
     * @param customer
     * @return true если подходят, false - иначе
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
