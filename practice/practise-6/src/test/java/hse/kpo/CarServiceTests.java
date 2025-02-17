package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyEngine;
import hse.kpo.domains.HandEngine;
import hse.kpo.domains.PedalEngine;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
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
    private CarService carService;

    @Test
    @DisplayName("CarService")
    void carServiceTest() {
        var handCarFactoryMock = Mockito.mock(HandCarFactory.class);
        Mockito.when(handCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class),
                Mockito.anyInt())).thenReturn(new Car(1, new HandEngine()));
        var pedalCarFactoryMock = Mockito.mock(PedalCarFactory.class);
        Mockito.when(pedalCarFactoryMock.createCar(Mockito.any(PedalEngineParams.class),
                Mockito.anyInt())).thenReturn(new Car(1, new PedalEngine(1)));
        var flyingCarFactoryMock = Mockito.spy(FlyingCarFactory.class);
        Mockito.when(flyingCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class),
                Mockito.anyInt())).thenReturn(new Car(1, new FlyEngine()));
        carService.addCar(handCarFactoryMock, EmptyEngineParams.DEFAULT);
        carService.addCar(flyingCarFactoryMock, EmptyEngineParams.DEFAULT);
    }

}
