package hse.kpo;

import hse.kpo.controllers.car.CarController;
import hse.kpo.interfaces.FacadeIterface;
import hse.kpo.services.HseCarService;
import hse.kpo.storages.CarStorage;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarControllerTest {

    @Autowired
    private FacadeIterface facade;

    @Autowired
    private CarController controller;

    @Autowired
    private CarStorage carStorage;

    @Autowired
    private HseCarService hseCarService;

    @BeforeEach
    public void setUp() {

    }

}
