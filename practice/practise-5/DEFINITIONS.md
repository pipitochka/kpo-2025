# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Порождающие паттерны - инкапсуляция логики создания объектов, чтобы можно было радоваться жизни
- Singleton (Одиночка) - как реализуется паттерн в джаве - создает только 1 объект
```java
public class Singleton {
    private static Abacaba instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Abacaba();  // Создаем экземпляр, если он еще не существует
        }
        return instance;
    }
}
```
- Factory (Фабрика) - как реализуется паттерн в джаве - делаем что-то, что будет само собирать что нам надо
```java
public interface Product {
    void doSomething();
}

public class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

public class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

public class ProductFactory {
    public Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else {
            return new ConcreteProductB();
        } 
    }
}
public class Client {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();

        Product productA = factory.createProduct("A");
        productA.doSomething(); 

        Product productB = factory.createProduct("B");
        productB.doSomething(); 
    }
}
```
- Fabric method (Фабричный метод) - как реализуется паттерн в джаве - как фабрика только мы еще делаем, что фабрики могут быть разными
```java
public interface Product {
    void doSomething();
}

public class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

public class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

public class ConcreteProductC implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

public class ConcreteProductD implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

public interface AbstractFactory {
    @Override
    public Product createProduct(String type);
}

public class ProductFactoryAB implements AbstractFactory {
    public Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else {
            return new ConcreteProductB();
        }
    }
}

public class ProductFactoryCD implements AbstractFactory {
    public Product createProduct(String type) {
        if (type.equals("C")) {
            return new ConcreteProductB();
        } else {
            return new ConcreteProductD();
        }
    }
}


public class Client {
    public static void main(String[] args) {
        ProductFactoryAB productFactoryAB = new ProductFactoryAB();
        ProductFactoryCD productFactoryCD = new ProductFactoryCD();
        productFactoryAB.createProduct("A").doSomething();
        productFactoryCD.createProduct("B").doSomething();
    }
}
```

- Abstract Factory (Абстрактная фабрика) - как реализуется паттерн в джаве - как прошлый паттерн, но еще круче
```java
public interface Product {
    void doSomething();
}

public class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

public class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

public class ConcreteProductC implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something");
    }
}

public class ConcreteProductD implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something");
    }
}

public interface AbstractFactory {
    @Override
    public Product createProduct(String type);
}

public class ProductFactoryAB implements AbstractFactory {
    public Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else {
            return new ConcreteProductB();
        }
    }
}

public class ProductFactoryCD implements AbstractFactory {
    public Product createProduct(String type) {
        if (type.equals("C")) {
            return new ConcreteProductB();
        } else {
            return new ConcreteProductD();
        }
    }
}

public class ToDo {
    AbstractFactory _factory;

    void doSomething(String type) {
        factory.createProduct(type).doSomething();
    }

    ToDo(AbstractFactory factory) {
        _factory = factory;
    }
}

public class Client {
    public static void main(String[] args) {
        ToDo toDo1 = new ToDo(new ProductFactoryAB());
        toDo1.doSomething("A");
        ToDo toDo2 = new ToDo(new ProductFactoryCD());
        toDo1.doSomething("C");
    }
}
```
- Builder (Строитель) - как реализуется паттерн в джаве
  Builder (Строитель) — абстракция, которая определяет шаги для создания объекта.
  ConcreteBuilder (Конкретный строитель) — класс, который реализует конкретную логику строительства.
  Director (Директор) — класс, который управляет процессом строительства.
  Product (Продукт) — объект, который создается.
```java
public class Car{
    String color;
    Car(String color){
        this.color = color;
    }
}

public abstract class CarBuilder {
    protected String color;
    
    public abstract CarBuilder setColor(String color);
    
    public abstract Car build();
}

public class ConcreteCarBuilder extends CarBuilder {
    @Override
    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }
    
    @Override
    public Car build() {
        return new Car(color, engineType, doors, airConditioning);
    }
}

public class Director {
    private CarBuilder carBuilder;

    public Director(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car constructRedCar() {
        return carBuilder.setColor("Red")
                .build();
    }

    public Car constructBlueCar() {
        return carBuilder.setColor("Blue")
                .build();
    }
}

```
- Prototype (Прототип) - как реализуется паттерн в джаве - нужен, когда создание нового объекта - дорогое удовольствие, проще скопировать старый
  Prototype (Прототип) — абстракция, которая объявляет метод клонирования.
  ConcretePrototype (Конкретный прототип) — конкретная реализация прототипа, которая реализует метод клонирования.
  Client (Клиент) — объект, который использует прототипы для создания новых экземпляров.
```java
public interface Prototype {
    Prototype clone();
}

public class Car implements Prototype {
    private String color;
    
    public Car(String color) {
        this.color = color;
    }
    
    @Override
    public Prototype clone() {
        return new Car(this.color);  // Создаем новый объект с такими же данными
    }
}
```
- стандартный синглтон плох, потому что в многопоточном режиме его могут создать несколько раз
  решение
```java
public class Singleton {
    private static Abacaba instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Abacaba();
        }
        return instance;
    }
    
}
```