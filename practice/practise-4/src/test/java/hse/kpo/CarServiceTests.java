package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyEngineInterface;
import hse.kpo.domains.HandEngineInterface;
import hse.kpo.domains.PedalEngineInterface;
import hse.kpo.factories.FlyingCarFactoryInterface;
import hse.kpo.factories.HandCarFactoryInterface;
import hse.kpo.factories.PedalCarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * class to test of a car service.
 */
@SpringBootTest
public class CarServiceTests {
    @Autowired
    private CarServiceInterface carService;

    @Test
    @DisplayName("CarService")
    void carServiceTest() {
        var handCarFactoryMock = Mockito.mock(HandCarFactoryInterface.class);
        Mockito.when(handCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class), Mockito.anyInt()))
                        .thenReturn(new Car(1, new HandEngineInterface()));
        var pedalCarFactoryMock = Mockito.mock(PedalCarFactoryInterface.class);
        Mockito.when(pedalCarFactoryMock.createCar(Mockito.any(PedalEngineParams.class), Mockito.anyInt()))
                .thenReturn(new Car(1, new PedalEngineInterface(1)));
        var flyingCarFactoryMock = Mockito.spy(FlyingCarFactoryInterface.class);
        Mockito.when(flyingCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class), Mockito.anyInt()))
                .thenReturn(new Car(1, new FlyEngineInterface()));
        carService.addCar(handCarFactoryMock, EmptyEngineParams.DEFAULT);
        carService.addCar(flyingCarFactoryMock, EmptyEngineParams.DEFAULT);
    }

}
