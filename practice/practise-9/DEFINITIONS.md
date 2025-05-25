# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- CRUD

Create-Read-Update-Delete - 4 базовых операции для работы с данными
- @Controller vs @RestController

@Controller - для возврата HTML страниц, подходит для MVC - приложений

@RestController = @Controller + @ResponseBody - тот же контроллер, но сериализовано в json, подходит для веб-сервисов и API

- @RequestMapping vs @GetMapping vs @PostMapping vs @PutMapping vs @DeleteMapping

Аннотации в спринг для работы с контроллерами

@RequestMapping - общая, нужно еще конкретно указывать метод 

```Java
@RequestMapping(value = "/users", method = RequestMethod.GET)
public List<User> getAllUsers() {
    return userService.findAll();
}
```

Остальные отвечают только за конкретный метод
1) GetMapping - Получение данных
2) PostMapping - Создание новой сущности
3) PutMapping - Полное обновление существующей сущности
4) DeleteMapping - Удаление сущности

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

- @PathVariable vs @RequestBody vs @RequestParam

Анотации в Spring для получения данных из разных частей запроса

1) @PathVariable - из URL-пути
2) @RequestBody - из параметров URL
3) @RequestParam - из тела запроса
 
```shell
POST /users
Content-Type: application/json

{
  "name": "Alice",
  "email": "alice@example.com"
}
```

```java
@GetMapping("/users/{id}")
public User getUserById(@PathVariable Long id) {
    return userService.findById(id);
}

@GetMapping("/search")
public List<User> search(@RequestParam String name) {
    return userService.searchByName(name);
}

@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.create(user);
}
```

- @Valid + @Pattern + @Min + @Max + @Nullable + @NotNull

Антонация в Spring, чтобы автоматически проверять данные 

| Аннотация     | Описание                                              | Пример                                      |
|---------------|--------------------------------------------------------|---------------------------------------------|
| `@Valid`      | Включает валидацию вложенного объекта                  | `@Valid @RequestBody UserDto dto`           |
| `@NotNull`    | Значение не может быть `null`                         | `@NotNull String name;`                     |
| `@Nullable`   | Разрешает `null`, **не валидирует** (информативно)    | `@Nullable String nickname;`                |
| `@Min(value)` | Минимальное значение для числового поля                | `@Min(18) int age;`                         |
| `@Max(value)` | Максимальное значение для числового поля               | `@Max(99) int age;`                         |
| `@Pattern`    | Строка должна соответствовать регулярному выражению    | `@Pattern(regexp = "\\d{10}") String phone;` |


- Swagger - что это + @Operation, OpenAPI

Набор инстурментов для документирования и тестирования

OpenAPI - формат документов 

@Operation - используется для описания контроллера в Swagger-документации
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(
        summary = "Получить пользователя по ID",
        description = "Этот метод возвращает пользователя по его уникальному идентификатору"
    )
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```

- +1 уникальный факт связанный с темами выше или семинаром

Swagger (OpenAPI) можно использовать для автоматической генерации клиентского кода на любом языке — Java, TypeScript, Python и даже C#.

Шаги:
1) Запуск проекта с OpenAPI и Swagger 
2) Экспорт openapi.json или openapi.yaml из /v3/api-docs
3) Отправка его в Swagger Codegen или OpenAPI Generator
4) Выбор нужного языка
5) Получаение готового SDK
