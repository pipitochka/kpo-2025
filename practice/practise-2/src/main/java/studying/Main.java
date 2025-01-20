package studying;

public class Main {
    public static void main(String[] args) {
        System.out.println("HSE");
        CarService carService = new CarService();
        CustomerStorage customerStorage = new CustomerStorage();
        HseCarService hseCarService = new HseCarService(carService, customerStorage);
        PedalCarFactory pedalCarFactory = new PedalCarFactory();
        HandCarFactory handCarFactory = new HandCarFactory();

        customerStorage.addCustomer(new Customer("1", 6, 4));
        customerStorage.addCustomer(new Customer("2", 4, 6));
        customerStorage.addCustomer(new Customer("3", 6, 6));
        customerStorage.addCustomer(new Customer("4", 4, 4));

        carService.addCar(pedalCarFactory, new PedalEngineParams(2));
        carService.addCar(pedalCarFactory, new PedalEngineParams(2));
        carService.addCar(handCarFactory, new EmptyEngineParams());
        carService.addCar(handCarFactory, new EmptyEngineParams());

        for (Customer customer : customerStorage.getCustomers()) {
            System.out.println(customer);
        }

        hseCarService.sellCars();

        for (Customer customer : customerStorage.getCustomers()) {
            System.out.println(customer);
        }


    }
}
