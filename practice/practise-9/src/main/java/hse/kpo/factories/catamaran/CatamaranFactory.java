package hse.kpo.factories.catamaran;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Ship;
import hse.kpo.domains.objects.Catamaran;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.interfaces.factories.CatamaranFactoryInterface;
import org.springframework.stereotype.Component;

/**
 * facroty to make willed catamarans.
 */
@Component
public class CatamaranFactory implements CarFactoryInterface<Ship> {

    @Override
    public Car createCar(Ship carParams, int carNumber) {
        Catamaran willedCatamaran = new Catamaran(carParams, carNumber);
        return willedCatamaran;
    }
}
