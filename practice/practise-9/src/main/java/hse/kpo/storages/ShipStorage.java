package hse.kpo.storages;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import hse.kpo.interfaces.providers.ShipProviderInterface;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * class of ship service.
 */
@Component
public class ShipStorage implements ShipProviderInterface {

    @Getter
    private final List<Ship> ships = new ArrayList<>();

    @Getter
    private int shipNumberCounter = 0;

    @Override
    public Ship takeShip(Customer customer) {

        var filteredShips = ships.stream().filter(ship -> ship.isCompatible(customer)).toList();

        var firstShip = filteredShips.stream().findFirst();

        firstShip.ifPresent(ships::remove);

        return firstShip.orElse(null);
    }

    /**
     * function which make and add ship to the pool.
     *
     * @param shipFactory factory which will make ship.
     * @param shipParams params for ship construcor.
     * @param <ParamsT> params for ship construcor.
     */
    public <ParamsT> void addShip(ShipFactoryInterface<ParamsT> shipFactory, ParamsT shipParams) {
        // создаем автомобиль из переданной фабрики
        var car = shipFactory.createShip(
                shipParams, // передаем параметры
                ++shipNumberCounter // передаем номер - номер будет начинаться с 1
        );

        ships.add(car); // добавляем автомобиль
    }
}
