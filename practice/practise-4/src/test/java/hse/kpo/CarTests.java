package hse.kpo;

import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CarTests {
    @Autowired
    private FlyingCarFactory flyingCarFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private PedalCarFactory pedalCarFactory;



    @Test
    @DisplayName("FlyingCarFactory")
    void FlyingCarFactoryTest() {
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

    @Test
    @DisplayName("HandCarFactory")
    void HandCarFactoryTest() {
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

    @Test
    @DisplayName("HandCarFactory")
    void PedalCarFactoryTest() {
//        var pedalParamsMock = Mockito.mock(PedalEngineParams.class);
//        Mockito.when(pedalParamsMock.pedalSize()).thenReturn(3);
//        Assertions.assertEquals(pedalCarFactory.createCar(pedalParamsMock, 1).getVIN(), 3);
////		pedalCarFactory.createCar(pedalParamsMock, 2);
////		pedalCarFactory.createCar(pedalParamsMock,  3);
//
//        verify(pedalParamsMock).pedalSize();
    }
}
