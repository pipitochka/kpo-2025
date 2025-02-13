package hse.kpo;

import static org.mockito.Mockito.verify;

import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * car tests.
 */
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
    void flyingCarFactoryTest() {
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

    @Test
    @DisplayName("HandCarFactory")
    void handCarFactoryTest() {
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

}
