package zoo;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zoo.domains.MyVetClinic;
import zoo.domains.Zoo;
import zoo.domains.animals.Animal;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.animals.Tiger;
import zoo.domains.animals.Wolf;
import zoo.domains.files.MyFileReader;
import zoo.domains.files.MyFileWriter;
import zoo.domains.things.Computer;
import zoo.domains.things.Table;
import zoo.domains.things.Thing;


/**
 * main class to entry in program.
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private Zoo zoo;

    @Autowired
    private MyFileReader myFileReader;

    @Autowired
    private MyFileWriter myFileWriter;

    @Autowired
    private MyVetClinic myVetClinic;
    /**
     * entry point of program.
     *
     * @param args special args.
     */

    public static void main(String[] args) {


    }

    @Override
    public void run(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int food;
        int number;
        int happiness;
        int i;
        List<Animal> animals;
        List<Thing> things;
        while (true) {
            String name = scanner.nextLine();
            if (name.equals("exit")) {
                break;
            }
            String[] split = name.split(" ");
            switch (split[0]) {
                case "add":
                    switch (split[1]) {
                        case "Monkey":
                            if (split.length != 5) {
                                System.out.println("Invalid input");
                                break;
                            }
                            food = Integer.valueOf(split[2]);
                            number = Integer.valueOf(split[3]);
                            happiness = Integer.valueOf(split[4]);
                            Monkey monkey = new Monkey(food, number, happiness);
                            zoo.addAnimal(monkey);
                            break;
                        case "Rabbit":
                            if (split.length != 5) {
                                System.out.println("Invalid input");
                                break;
                            }
                            food = Integer.valueOf(split[2]);
                            number = Integer.valueOf(split[3]);
                            happiness = Integer.valueOf(split[4]);
                            Rabbit rabbit = new Rabbit(food, number, happiness);
                            zoo.addAnimal(rabbit);
                            break;
                        case "Tiger":
                            if (split.length != 4) {
                                System.out.println("Invalid input");
                                break;
                            }
                            food = Integer.valueOf(split[2]);
                            number = Integer.valueOf(split[3]);
                            Tiger tiger = new Tiger(food, number);
                            zoo.addAnimal(tiger);
                            break;
                        case "Wolf":
                            if (split.length != 4) {
                                System.out.println("Invalid input");
                                break;
                            }
                            food = Integer.valueOf(split[2]);
                            number = Integer.valueOf(split[3]);
                            Wolf wolf = new Wolf(food, number);
                            zoo.addAnimal(wolf);
                            break;
                        case "Computer":
                            if (split.length != 3) {
                                System.out.println("Invalid input");
                                break;
                            }
                            number = Integer.valueOf(split[2]);
                            Computer computer = new Computer(number);
                            zoo.addThing(computer);
                            break;
                        case "Table":
                            if (split.length != 3) {
                                System.out.println("Invalid input");
                                break;
                            }
                            number = Integer.valueOf(split[2]);
                            Table table = new Table(number);
                            zoo.addThing(table);
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                    break;
                case "getNumberAnimals":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    System.out.println(zoo.getNumberOfAnimals());
                    break;
                case "getNumberThings":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    System.out.println(zoo.getNumberOfThings());
                    break;
                case "ContactZoo":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    animals = zoo.getAnimalsToContactZoo();
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                    break;
                case "getAnimals":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    animals = zoo.getAnimals();
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                    break;
                case "getThings":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    things = zoo.getThings();
                    for (Thing thing : things) {
                        System.out.println(thing);
                    }
                    break;
                case "getInfoAboutAnimal":
                    if (split.length != 2) {
                        System.out.println("Invalid input");
                        break;
                    }
                    i = Integer.parseInt(split[1]);
                    if (i > zoo.getNumberOfAnimals()) {
                        System.out.println("There are no animals available");
                        break;
                    }
                    System.out.println(zoo.getAnimals().get(i));
                    break;
                case "getInfoAboutThing":
                    if (split.length != 2) {
                        System.out.println("Invalid input");
                        break;
                    }
                    i = Integer.parseInt(split[1]);
                    if (i > zoo.getNumberOfThings()) {
                        System.out.println("There are no animals available");
                        break;
                    }
                    System.out.println(zoo.getThings().get(i));
                    break;
                case "saveInformation":
                    if (split.length != 2) {
                        System.out.println("Invalid input");
                        break;
                    }
                    myFileWriter.writeFile(split[1], zoo);
                    break;
                case "takeInformation":
                    if (split.length != 2) {
                        System.out.println("Invalid input");
                        break;
                    }
                    zoo = myFileReader.readFromFile(split[1]);
                    break;
                case "getFoodInformation":
                    if (split.length != 1) {
                        System.out.println("Invalid input");
                        break;
                    }
                    System.out.println(zoo.calculateFood());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command");
            }
        }

    }
}