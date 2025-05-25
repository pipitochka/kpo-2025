# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Что такое Docker? зачем он нужен?
Инструмент для запуска приожения, как будто из коробки на любом устройстве, что делает приложение платформонезависимым
- Как поднять бд в докере?
docker-compose.yml, с запуском через docker-compose up -d, в файле описано как устроен контейнер
пример
```shell
services:
  postgres:
    image: postgres:15-alpine
    container_name: postgres_db
    environment:
      POSTGRES_DB: car_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d postgres"]
      interval: 5s
      timeout: 5s
      retries: 5

  app:
    build: .
    container_name: car_app
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/car_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
      SPRING_JPA_HIBERNATE_DDL_AUTO: none
    ports:
      - "8080:8080"
    depends_on:
      postgres:
        condition: service_healthy

volumes:
  postgres_data:
```
- Как подключить бд к приложению?
через application.yml
```shell
spring:
  application:
    name: kpo-app
  datasource:
    url: jdbc:postgresql://localhost:5432/car_db
    username: postgres
    password: postgres
    #    url: ${SPRING_DATASOURCE_URL}
    #    username: ${SPRING_DATASOURCE_USERNAME}
    #    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    #      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO}
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
server:
  port: 8080
```
- Что такое Repository?
Патерн проектирования, отвечающий за работу с данными, инкаспулирующий логику хранения
- Что такое Hibernate?
Популярная библиотека для работы с базами данных, JPA под капотом имеет эту библиотеку
- Связь one-one, one-many, many-many, как делается в джаве?
```java
@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;

@ManyToMany
@JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();
```
Виды соотношения связей для бд, один-один(машна - двигатель), один-много (курьер-заказ), много-много(студенты-пары)

- Как сделать общую таблицу для разных обьектов?
```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {
    private int numberOfDoors;
}

@Entity
@DiscriminatorValue("SHIP")
public class Ship extends Vehicle {
    private double tonnage;
}
```
например так, неиспользуемые поля будут null

- +1 уникальный факт связанный с темами выше или семинаром
после изменения кода нужно обязательно пересобирать образ 
```shell
docker-compose down
docker-compose up --build -d
```
иначе изменения не встпят в силу и в контейнере будет собрана старая версия