# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Поведенческие паттерны - группы шаблонов проектирования, определяющие взаимодействие объектов и делающие
код более гибким и легко маштабируемым.
- Цепочка обязанностей (Chain of responsibility) - дает возможность передавать запрос по цепочке, чтобы его обрабатывал конкретный элемент
```java
abstract class Logger {
    protected Logger next; 

    public void setNext(Logger next) {
        this.next = next;
    }

    public void log(String message, String level) {
        if (handle(message, level) && next != null) {
            next.log(message, level);
        }
    }
    
    protected abstract boolean handle(String message, String level);
}

class FirstLogger extends Logger {
    @Override
    protected boolean handle(String message, String level) {
        if (level.equals("a")) {
            System.out.println("a " + message);
            return false;
        }
        return true; 
    }
}

class SecondLogger extends Logger {
    @Override
    protected boolean handle(String message, String level) {
        if (level.equals("b")) {
            System.out.println("b" + message);
            return false;
        }
        return true;
    }
}
```
Использование
```java
Logger firstLogger = new FirstLogger();
Logger secondLogger = new SecondLogger();

firstLogger.setNext(secondLogger);

firstLogger.log("abacaba", "a");
firstLogger.log("abacaba", "b");
```
- Команда (Command) - превращаем запросы в обеъекты, чтобы было можно легче их передавать, логировать и повторять
```java
interface Command {
    void execute();
    void undo();
}

class Light {
    public void turnOn() {
        System.out.println("Свет включен");
    }

    public void turnOff() {
        System.out.println("Свет выключен");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}
```
Использование
```java
Light light = new Light();
Command lightOn = new LightOnCommand(light);
Command lightOff = new LightOffCommand(light);
RemoteControl remote = new RemoteControl();
remote.setCommand(lightOn);
remote.pressButton();
```
- Интерпретатор (Interpreter) - для обработки языков, в зависимости от распаршенного выражения решает что делать
```java
interface Expression {
    int interpret();
}
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}
class AdditionExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AdditionExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}
```
Использование
```java
Stack<Expression> stack = new Stack<>();

String[] tokens = expression.split(" ");
for (String token : tokens) {
    if (token.equals("+")) {
        Expression right = stack.pop();
        Expression left = stack.pop();
        stack.push(new AdditionExpression(left, right));
    }  else {
        stack.push(new NumberExpression(Integer.parseInt(token)));
    }
}
```
- Итератор (Iterator) - для последовательного обхода структуры
```java
interface Iterator<T> {
    boolean hasNext();  // Проверяет, есть ли следующий элемент
    T next();           // Возвращает следующий элемент
}

interface IterableCollection<T> {
    Iterator<T> createIterator();
}

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Zoo implements IterableCollection<Animal> {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public Iterator<Animal> createIterator() {
        return new ZooIterator(this.animals);
    }
}

class ZooIterator implements Iterator<Animal> {
    private List<Animal> animals;
    private int position = 0;

    public ZooIterator(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean hasNext() {
        return position < animals.size();
    }

    @Override
    public Animal next() {
        return animals.get(position++);
    }
}
```
Использование
```java
Zoo zoo = new Zoo();
zoo.addAnimal(new Animal("Лев"));
zoo.addAnimal(new Animal("Тигр"));
zoo.addAnimal(new Animal("Обезьяна"));

Iterator<Animal> iterator = zoo.createIterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next().getName());
}
```
- Посредник (Mediator) - делаем взимодейтствие всех объектов через посредика
```java
interface Mediator {
    void sendMessage(String message, Employee sender);
}

abstract class Employee {
    protected Mediator mediator;
    protected String name;

    public Employee(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class ZooMediator implements Mediator {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void sendMessage(String message, Employee sender) {
        for (Employee employee : employees) {
            if (employee != sender) {
                employee.receive(message);
            }
        }
    }
}

class Vet extends Employee {
    public Vet(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " (ветеринар) отправил сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " (ветеринар) получил сообщение: " + message);
    }
}

class Zookeeper extends Employee {
    public Zookeeper(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " (смотритель) отправил сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " (смотритель) получил сообщение: " + message);
    }
}
```
Использвание 
```java
ZooMediator mediator = new ZooMediator();

Vet vet = new Vet(mediator, "Анна");
Zookeeper zookeeper = new Zookeeper(mediator, "Иван");

mediator.addEmployee(vet);
mediator.addEmployee(zookeeper);

vet.send("Лев заболел, нужна помощь!");
```
- Хранитель (Memento) - позволяет сохранять и восстанавливать изменения
```java
class ZooMemento {
    private final List<String> animals;

    public ZooMemento(List<String> animals) {
        this.animals = new ArrayList<>(animals); 
    }

    public List<String> getAnimals() {
        return animals;
    }
}
class Zoo {
    private List<String> animals = new ArrayList<>();

    public void addAnimal(String animal) {
        animals.add(animal);
        System.out.println("Добавлено животное: " + animal);
    }

    public void removeAnimal(String animal) {
        animals.remove(animal);
        System.out.println("Удалено животное: " + animal);
    }

    public void showAnimals() {
        System.out.println("Животные в зоопарке: " + animals);
    }

    public ZooMemento save() {
        return new ZooMemento(animals);
    }

    public void restore(ZooMemento memento) {
        animals = memento.getAnimals();
        System.out.println("Состояние зоопарка восстановлено!");
    }
}
class Caretaker {
    private Stack<ZooMemento> history = new Stack<>();

    public void saveState(Zoo zoo) {
        history.push(zoo.save());
        System.out.println("Состояние сохранено.");
    }

    public void undo(Zoo zoo) {
        if (!history.isEmpty()) {
            zoo.restore(history.pop());
        } else {
            System.out.println("Нет сохраненных состояний.");
        }
    }
}
```
Использование
```java
Zoo zoo = new Zoo();
Caretaker caretaker = new Caretaker();

zoo.addAnimal("Тигр");
caretaker.saveState(zoo); 

zoo.addAnimal("Обезьяна");
        
caretaker.undo(zoo); 
```
- Наблюдатель (Observer) - дает возможность одним объектам следить за изменениями в других
```java
interface Observer {
    void update(String message);
}
interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
class Zoo implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private List<String> animals = new ArrayList<>();

    // Добавление наблюдателя
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Удаление наблюдателя
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Уведомление всех наблюдателей
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Добавление животного в зоопарк
    public void addAnimal(String animal) {
        animals.add(animal);
        System.out.println("Добавлено новое животное: " + animal);
        notifyObservers("В зоопарке появилось новое животное: " + animal);
    }
}
class Visitor implements Observer {
    private String name;

    public Visitor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " получил уведомление: " + message);
    }
}
```
Использование
```java
Zoo zoo = new Zoo();

Visitor visitor1 = new Visitor("Анна");
Visitor visitor2 = new Visitor("Дмитрий");

zoo.addObserver(visitor1);
zoo.addObserver(visitor2);

zoo.addAnimal("Тигр");
zoo.addAnimal("Слон");
```
- Состояние (State) - меняем состояние в зависимости от внутреннего состояния
```java
interface GateState {
    void open(Gate gate);
    void close(Gate gate);
}
class ClosedState implements GateState {
    @Override
    public void open(Gate gate) {
        System.out.println("Открываем ворота");
        gate.setState(new OpenState());
    }

    @Override
    public void close(Gate gate) {
        System.out.println("Ворота уже закрыты");
    }
}
class OpenState implements GateState {
    @Override
    public void open(Gate gate) {
        System.out.println("Ворота уже открыты");
    }

    @Override
    public void close(Gate gate) {
        System.out.println("Закрываем ворота");
        gate.setState(new ClosedState());
    }
}
class Gate {
    private GateState state;

    public Gate() {
        this.state = new ClosedState(); 
    }

    public void setState(GateState state) {
        this.state = state;
    }

    public void open() {
        state.open(this);
    }

    public void close() {
        state.close(this);
    }
}
```
Использование
```java
Gate gate = new Gate();

gate.open(); 
gate.close(); 
```
- Стратегия (Strategy) - меняемся поведение в зависимости от чего-то 
```java
interface SoundStrategy {
    void makeSound();
}
class RoarSound implements SoundStrategy {
    @Override
    public void makeSound() {
        System.out.println("Тигр рычит");
    }
}
class HowlSound implements SoundStrategy {
    @Override
    public void makeSound() {
        System.out.println("Волк воет");
    }
}
class Animal {
    private String name;
    private SoundStrategy soundStrategy;

    public Animal(String name, SoundStrategy soundStrategy) {
        this.name = name;
        this.soundStrategy = soundStrategy;
    }

    public void makeSound() {
        System.out.print(name + ": ");
        soundStrategy.makeSound();
    }
}
```
- Шаблонный метод (Template method) - позволяет подклассам использовать уже написанную логику! важно сама логика должна быть описана заранее, но часть ее реализации може быть оставлена на потомков
```java
abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    void day(){
        voice();
        eat();
        voice();
    }
    
    void eat(){
        System.out.println("animal eats");
    }
    
    abstract void voice();
}

class Wolf extends Animal{
    public Wolf(String name){
        super(name);
    }
    
    @Override
    void voice(){
        System.out.println("UUU");
    }
}
class Ship extends Animal{
    public Ship(String name){
        super(name);
    }

    @Override
    void voice(){
        System.out.println("BEEE");
    }
}
```

- Посетитель  (Visitor) - добавление новой операции над уже готовыми объектами 
```java
interface AnimalVisitor {
    void visit(Wolf wolf);
    void visit(Ship ship);
}

interface Animal {
    void accept(AnimalVisitor visitor);
}

class Wolf implements Animal {
    private String name;

    public Wolf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(AnimalVisitor visitor) {
        visitor.visit(this);
    }
}

class Ship implements Animal {
    private String name;

    public Ship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(AnimalVisitor visitor) {
        visitor.visit(this);
    }
}

class AnimalActionVisitor implements AnimalVisitor {
    @Override
    public void visit(Wolf wolf) {
        System.out.println(wolf.getName() + " is eating meat.");
    }

    @Override
    public void visit(Ship ship) {
        System.out.println(ship.getName() + " is grazing grass.");
    }
}
```
- «Стратегия» — это разные способы выполнения одной и той же задачи, а «Состояние» — это разные этапы в жизненном цикле объекта.