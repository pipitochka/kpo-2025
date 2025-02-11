package hse.kpo;

import hse.kpo.factories.FlyingCarFactoryInterface;
import hse.kpo.factories.HandCarFactoryInterface;
import hse.kpo.factories.PedalCarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * class of car tests.
 */
@SpringBootTest
public class CarTests {
    @Autowired
    private FlyingCarFactoryInterface flyingCarFactory;

    @Autowired
    private HandCarFactoryInterface handCarFactory;

    @Autowired
    private PedalCarFactoryInterface pedalCarFactory;



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

    @Test
    @DisplayName("HandCarFactory")
    void pedalCarFactoryTest() {
    }
}
