package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.ShipFactoryInterface;
import hse.kpo.interfaces.ShipProviderInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShipService implements ShipProviderInterface {

    private final List<Ship> ships = new ArrayList<>();

    private int shipNumberCounter = 0;

    @Override
    public Ship takeShip(Customer customer) {

        var filteredShips = ships.stream().filter(ship -> ship.isCompatible(customer)).toList();

        var firstShip = filteredShips.stream().findFirst();

        firstShip.ifPresent(ships::remove);

        return firstShip.orElse(null);
    }

    public <ParamsT> void addShip(ShipFactoryInterface<ParamsT> shipFactory, ParamsT shipParams) {
        // создаем автомобиль из переданной фабрики
        var car = shipFactory.createShip(
                shipParams, // передаем параметры
                ++shipNumberCounter // передаем номер - номер будет начинаться с 1
        );

        ships.add(car); // добавляем автомобиль
    }
}
