package hse.kpo;

import static org.mockito.Mockito.verify;

import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
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
        flyingCarFactory.create(EmptyEngineParams.DEFAULT);
        flyingCarFactory.create(EmptyEngineParams.DEFAULT);
        flyingCarFactory.create(EmptyEngineParams.DEFAULT);
    }

    @Test
    @DisplayName("HandCarFactory")
    void handCarFactoryTest() {
        handCarFactory.create(EmptyEngineParams.DEFAULT);
        handCarFactory.create(EmptyEngineParams.DEFAULT);
        handCarFactory.create(EmptyEngineParams.DEFAULT);
    }

}
