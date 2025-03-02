# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Структурные паттерны - нужны для организации взаимодейтсвия компонентов системы, помогают упростить разработку и внедрение новых сущностей
- Декоратор (Decorator) - расширяет функционал, не меняя изначальную сущность
```java
interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() { return 5; }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    private bool isMilkInside = false;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public double cost() { return coffee.cost() + 2; }
    public void addMilk() {isMilkInside = true; }
}
```
- Адаптер (Adapter) - нужен для замены одной сущности на другую (на вход приходит старая, на выход имеем новую)
```java
class OldSystem {
    void oldMethod() {
        System.out.println("Old system working...");
    }
}

interface NewSystem {
    void newMethod();
}

class Adapter implements NewSystem {
    private OldSystem oldSystem;

    public Adapter(OldSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public void newMethod() {
        oldSystem.oldMethod();
    }
}
```
- Фасад (Facade) - входная точка для сложной системы, позволяет скрыть сложные реализации
Фасад HSE который содержит все внутри, а пользователю предоставляет простую логику правления программой
- Заместитель (Прокси) - управляет доступом к дургому объекту(замещая его), чаще всего используется, если у нас есть тяжелые объекты, но мы не знаем понадобятся ли они
```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    public RealImage(String filename) { this.filename = filename; load(); }
    private void load() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    public ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (realImage == null) realImage = new RealImage(filename);
        realImage.display();
    }
}
```
- Компоновщик (Composite) - позволяет работать с группой объектов как с одним объектом этой группы
```java
interface Component {
    void show();
}

class File implements Component {
    private String name;
    public File(String name) { this.name = name; }
    public void show() { System.out.println("File: " + name); }
}

class Folder implements Component {
    private List<Component> components = new ArrayList<>();
    public void add(Component component) { components.add(component); }
    public void show() { for (Component c : components) c.show(); }
}

```
- Мост (Bridge) - разделяет абстракцию и реализацию, чтобы одно могло меняться вне зависимости от другого
```java
interface MessageSender {
    void send(String message);
}

class EmailSender implements MessageSender {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

abstract class Message {
    protected MessageSender sender;
    public Message(MessageSender sender) { this.sender = sender; }
    abstract void sendMessage(String text);
}

class TextMessage extends Message {
    public TextMessage(MessageSender sender) { super(sender); }
    void sendMessage(String text) { sender.send(text); }
}

```
- Приспособленец (Flyweight) - разделяем все объекты на общие части и конкретные, допустим для пользователей храним адрес фотографии в базе, что в случае одинаковых фоток дает нам выигрыш в памяти
```java
class Avatar {
    private final String url;

    public Avatar(String url) {
        this.url = url;
    }

    public void display(String username) {
        System.out.println(username + " использует аватар: " + url);
    }
}

class AvatarFactory {
    private static final Map<String, Avatar> avatars = new HashMap<>();

    public static Avatar getAvatar(String url) {
        avatars.putIfAbsent(url, new Avatar(url));
        return avatars.get(url);
    }
}



public class Chat {
    public static void main(String[] args) {
        Avatar a1 = AvatarFactory.getAvatar("avatar1.png");
        Avatar a2 = AvatarFactory.getAvatar("avatar1.png"); // Тот же объект!

        a1.display("Артем");
        a2.display("Иван");

        System.out.println("Количество созданных аватаров: " + AvatarFactory.avatars.size()); // 1
    }
}

```
- +1 уникальный факт связанный с темами выше или семинаром
Приспособленеца очень часто используют в играх, когда надо создавать условные деревья или дома
в таком случае тяжелая картинка дома загружается создается в программе лишь один раз, даже если используется несколько
Аналогично с деревьями