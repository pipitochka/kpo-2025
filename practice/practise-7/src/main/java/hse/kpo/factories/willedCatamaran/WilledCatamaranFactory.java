package hse.kpo.factories.catamaran;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Ship;
import hse.kpo.domains.objects.WilledCatamaran;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import org.springframework.stereotype.Component;

@Component
public class WilledCatamaranFactory implements CarFactoryInterface<Ship> {
    @Override
    public Car createCar(Ship carParams, int carNumber) {
        WilledCatamaran willedCatamaran = new WilledCatamaran(carParams, carNumber);
        return willedCatamaran;
    }
}
