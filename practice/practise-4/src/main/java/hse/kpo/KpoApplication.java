package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpoApplication.class, args);

		var carService = new CarService();

		var customerStorage = new CustomerStorage();

		var hseCarService = new HseCarService(carService, customerStorage);

		var pedalCarFactory = new PedalCarFactory();

		var handCarFactory = new HandCarFactory();

		var flyingCarFactory = new FlyingCarFactory();

		customerStorage.addCustomer(new Customer("Ivan1",6,4));
		customerStorage.addCustomer(new Customer("Maksim",4,6));
		customerStorage.addCustomer(new Customer("Petya",6,6));
		customerStorage.addCustomer(new Customer("Nikita",4,4));
		customerStorage.addCustomer(new Customer("Artem",4,4, 350));

		carService.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);

		carService.addCar(pedalCarFactory, new PedalEngineParams(6));
		carService.addCar(pedalCarFactory, new PedalEngineParams(6));

		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

		hseCarService.sellCars();

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
	}
}
