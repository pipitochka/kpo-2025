# ДЗ 2
реализация консольного приложения для банка

## Содержание
- [Запуск](#запуск)
- [Логика](#логика)
- [Реализация](#реализация)
- [Добавление](#добавление)
- [SOLID](#SOLID)
- [DI](#DI)
- [GRASP](#GRASP)
- [Паттерны](#Паттерны)

## Запуск
Чтобы запустить приложение запустите функцию main в классе Main.  
Далее работа осуществляется через консоль
Набор доступных команд
```shell
add account <name> - создать пользователя
add category <expense/income> <name> - создать категорию
add operation <expense/income> <accountId/accountName> <amount> <date> <descripition> <categoryId/categoryName> - создать операцию
add operation <expense/income> <accountId/accountName> <amount> <date> <categoryId/categoryName> - создать операцию
show accounts - показать пользователей
show categories - показать категории
show operations - показать операции
analyse account <accountId/accountName> from <dateFrom> to <dateTo> - вывести анализ аккаунта за период
analyse income <accountId/accountName> from <dateFrom> to <dateTo> - вывести заработок аккаунта за период
analyse expense <accountId/accountName> from <dateFrom> to <dateTo> - вывести траты аккаунта за период
analyse account <accountId/accountName> by <categoryName/categoryId> from <dateFrom> to <dateTo> - вывести анализ аккаунта по категории
change operation <operationId> category to <categoryName/categoryId> - поменять категорию операции 
delete account <accountId/accountName> - удалить аккаунт
delete category <categoryId/categoryName> - удалить категорию
repeat <accountId/accountName> - повторить все операции для аккаунта
reverse <operationId> - отменить операцию
save <null/path> - сохраняет 
take <null/path> - загружает
```

Пример возможной работы
```shell
add account Artem
add account Aliya
add category expense cafe
add category expense coffee
add category income cashback
add category income salary
add operation income 0 200 0 salary 5
add operation income Artem 200 1 salary
add operation income Aliya 200 2 salary salary
add operation income Artem 100 2 cashback
add operation expense Aliya 100 3 coffee
add operation expense Artem 100 4 coffee
add operation expense Aliya 100 4 coffee
show accounts
show categories
show operations
analyse account Artem from 1 to 2
analyse account Aliya from 1 to 4
analyse account Aliya by coffee from 3 to 4
analyse income Artem from 1 to 1
analyse expense Aliya from 3 to 4
change operation 5 category to cafe 
analyse account Artem from 1 to 4
delete account 1
show operations
delete category cafe
show categories
change operation 5 category to coffee
show operations
show accounts
reverse operation 3
show accounts
save
exit
```

## Логика
Банк завязан на пользователях, операциях и категориях (интерфейсы)
Для их создания используем фабрики
Есть фасад для удобной работы с банком
Для удобной работы с консолью реализован интерфейс комманды, чтобы мы могли анализировать комманды независимо от их типа, в следствии чего реализован контекст для комманд
Команды бывают 3 видов - добавить пользователя, добавить операцию, добавить категорию
Для анализа команд используется цепочка отвественности и handlerы, которые анализируют команды в зависимости от их типа

## Реализация
Стурктура
```shell
/src
├── zoo.KpoApplication.java                        <-- Точка входа в приложение  
│    ├── /domain                               <-- Пакет для доменных объектов, представляющих данные
│    │    ├── /command                         <-- Пакет для классов, связанных с командами, реализует интерфейс команды
│    │    │    ├── CommandFromOpperatiom.java     <-- Адаптер для операции, чтобы можно было превратить ее в команду 
│    │    │    ├── HseAddBankAccountCommand.java  <-- Команда для добавления пользователя
│    │    │    ├── HseAddOperationCommand.java    <-- Команда для добавления операции
│    │    │    ├── HseAddCategoryCommand.java     <-- Команда для добавления категории
│    │    │
│    │    ├── /facade           <-- Пакет для фасадов  
│    │    │    ├── HseFacade.java         <-- Конкретный чтобы упростить работу с банком
│    │    │
│    │    ├── /factory                       <-- Пакет для классов, с созданием объектов 
│    │    │    ├── HseAccountFactory.java    <-- Фабрика для пользователей
│    │    │    ├── HseCategoryFactory.java   <-- Фабрика для категорий
│    │    │    ├── HseOperationFactory.java  <-- Фабрика для операций
│    │    │    ├── HseCommandBuilder.java    <-- Фабрика для комманд
|    |    |
│    │    ├── /handler          <-- Пакет для классов, связанных с обработкой данных 
│    │    │    ├── StartHandler.java            <-- Отвечает за создание цепочки обработчиков
│    │    │    ├── ErrorHandler.java            <-- Отвечает за случай если никто не обработал 
│    │    │    ├── HseAccountHandler.java       <-- Отвечает за добавление пользователя (имя не совпадает)
│    │    │    ├── HseAccountSumHandler.java    <-- Отвечает за корректность операции (нельзя уйти в минус)
│    │    │    ├── HseCategoryHandler.java      <-- Отвечает за категории пользователя (имя не совпадает)
│    │    │    ├── HseOperationHandler.java     <-- Отвечает за категории пользователя (есть пользователь и категория)
|    |    |
│    │    ├── /object                  <-- Пакет для классов, содержащих данные  
│    │    │    ├── HseAccount.java              <-- Класс пользователей
│    │    │    ├── HseCategory.java             <-- Класс категорий
│    │    │    ├── HseOperation.java            <-- Класс операций
│    │    │    ├── HseCommandContext.java       <-- Класс контекста
│    │    
│    ├── /enums                 <-- Пакет для перечислений
|    |    ├── CommandType             <-- Тип комманды
|    |    ├── OperationType           <-- Тип операции
|    |
│    ├── /file                  <-- Пакет для работы с файлами
|    |    ├── /classes                <-- Пакет для реализаций экспортеров и импортеров
|    |    |    ├── FileImporter               <-- Абстрактный класс для загрузчика
|    |    |    ├── JsonFileImporter           <-- Реализация нужного нам загрузчика
|    |    |    ├── JsoneFileExporter          <-- Реализация импортера
|    |    |
|    |    ├── /interfaces             <-- Пакет интерфейсов
|    |    |    ├── OperationType              <-- Интерфейс класса который надо сохранить
|    |    |    ├── OperationType              <-- Интрефейс сохранятеля
│    │    
│    ├── /interfaces              <-- Пакет для интерфейсов
|    │    ├── /factory                 <-- Интерфейсы фабрик  
│    │    │    ├── AccountFactory.java        <-- Интерфейс фабрики пользователей
│    │    │    ├── CategoryFactory.java       <-- Интерфейс фабрики категорий
│    │    │    ├── OperationFactory.java      <-- Интерфейс фабрики операций
│    │    │    ├── CommandBuilder.java        <-- Интерфейс фабрики комманд
|    |    |
|    │    ├── /object                  <-- Пакет для классов, содержащих данные  
│    │    │    ├── Account.java               <-- Интерфейс пользователя
│    │    │    ├── Category.java              <-- Интерфейс категории
│    │    │    ├── Operation.java             <-- Интерфейс операции
│    │    │    ├── CommandContext.java        <-- Интерфейс контекста
│    │    │    ├── Command.java               <-- Интерфейс комманды
│    │    │    ├── Facade.java                <-- Интерфейс фасада
│    │    │    ├── OperationHandler.java      <-- Интерфейс обработчика
```

## Добавление
Для добавления нового вида анализа нужно 
- Проинициилизировать метод в интерфейсе фасада
- Написать конкретную реализацию в ХсеФасаде
- Добавить в ConsoleApp обработку новой комманды
- Если необходима обработка события, создать новый Myhandler имплементировав в него интерфейс handler, после чего подключить его в HseStartHandler

## SOLID
- S - много классов и интерфесов, которые отвечают за маленькие объемы функционала
- O - реализация закрыта для модификации, но открыта для расширения (легко добавить новых животных)
- L - CommandFromOperation может быть использован вместо HseAddBankAccountCommand
- I - Много интерфейсов, вообще у всего есть интерфейс кроме самого класса зоопарка
- D - Все зависимости строятся на зависимостях интерфейсов, что значит реализации на самом деле независимы

## DI
- DI - использую для автоматического внедрения зависимостей (например фасадом и фабриками)
Касательно тестов, ими покрыто 89 процента кода 

## GRASP
- Information Expert (Информационный эксперт) - каждый класс владеет только тем, за что он отвечает
- Creator (Создатель) - создание происходит только в фасаде, который и владеет ресурсами, при помощи фабрик
- Controller (Контроллер) - за обработку отвечает фасад
- Low Coupling - зависимость только на уровне интерфейсов
- High Cohesion - все классы выполняют определенные задачи связанные с ними
- Polymorphism - привет интерфейсы
- Pure Fabrication - создание новых объектов только на фабриках
- Indirection - возможно реализуется за счет цепочки вызовов обработчиков (не все происходит в одном месте)
- Protected Variations - зависимости на уровне интерфейсов, значит изменения в одном месте не затронут остальной код

## Патерны
- Фасад - HseFacade - для работы с банком в более удобном варианте
- Команда - все запросы передаются как команды, для удобства обработки
- Фабрика - все новые объекты создаются на фабриках
- Адаптер - для операции, чтобы их можно было обратно обратить в команды (например для пересчета человека)
- Цепочка обязанностей - для обработки запросов
- Посетитель - для экпортера 
- Шаблонный метод - для импорта