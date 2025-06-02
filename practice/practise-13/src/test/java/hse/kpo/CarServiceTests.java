package hse.kpo;

import hse.kpo.domains.engines.FlyEngine;
import hse.kpo.domains.engines.HandEngine;
import hse.kpo.domains.engines.PedalEngine;
import hse.kpo.domains.objects.Car;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * car service tests.
 */
@SpringBootTest
public class CarServiceTests {
    @Autowired
    private CarProviderInterface carStorage;

    @Test
    @DisplayName("CarService")
    void carServiceTest() {
        var handCarFactoryMock = Mockito.mock(HandCarFactory.class);
        Mockito.when(handCarFactoryMock.create(Mockito.any(EmptyEngineParams.class)))
                .thenReturn(new Car(1, new HandEngine()));
        var pedalCarFactoryMock = Mockito.mock(PedalCarFactory.class);
        Mockito.when(pedalCarFactoryMock.create(Mockito.any(PedalEngineParams.class)))
                .thenReturn(new Car(1, new PedalEngine(1)));
        var flyingCarFactoryMock = Mockito.spy(FlyingCarFactory.class);
        Mockito.when(flyingCarFactoryMock.create(Mockito.any(EmptyEngineParams.class)))
                .thenReturn(new Car(1, new FlyEngine()));
        carStorage.addCar(handCarFactoryMock, EmptyEngineParams.DEFAULT);
        carStorage.addCar(flyingCarFactoryMock, EmptyEngineParams.DEFAULT);
    }

}
