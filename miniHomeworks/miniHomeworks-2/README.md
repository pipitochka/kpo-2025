# МинниДЗ 2 — Веб-приложение для управления зоопарком

## Описание проекта

Приложение реализует функциональность по управлению животными, вольерами, графиками кормления и статистикой зоопарка. Архитектура построена по принципам **Domain-Driven Design (DDD)** и **Clean Architecture**, с четким разделением слоев и зависимостей.

---

## Реализованный функционал и классы/модули

### 1. Управление животными

- **Реализовано:** создание, редактирование, перемещение животных.
- **Модули:**
    - `Animal` — сущность животного.
    - `AnimalMovedEvent` — событие перемещения.
    - `AnimalTransferService` — логика перемещения (слой Application).
    - `AnimalController` — REST API-обработчик.
    - `CreateAnimalRequest`, `AnimalDTO` — DTO.
    - `AnimalConverter` — преобразование между DTO и Entity.

---

### 2. Управление вольерами

- **Реализовано:** добавление вольеров, размещение животных, проверка вместимости.
- **Модули:**
    - `Enclosure` — сущность вольера.
    - `EnclosureController` — API-контроллер.
    - `CreateEnclosureRequest`, `EnclosureDTO` — DTO.
    - `EnclosureConverter` — маппинг DTO <-> Entity.

---

### 3. Графики кормления

- **Реализовано:** управление расписанием кормлений.
- **Модули:**
    - `FeedingSchedule` — сущность расписания кормления.
    - `FeedingScheduleCreatedEvent` — событие о создании графика.
    - `FeedingOrganizationService` — логика по организации кормления.
    - `FeedingScheduleController` — API-контроллер.
    - `CreateFeedingScheduleRequest`, `FeedingScheduleDto` — DTO.
    - `FeedingScheduleConverter` — преобразователь.

---

### 4. Статистика

- **Реализовано:** сбор и отображение статистики по животным, вольерам и кормлениям.
- **Модули:**
    - `ZooStatisticService` — логика сбора статистики.
    - `StatisticController` — REST API-контроллер.

---

## Domain-Driven Design (DDD)

### Сущности (Entities)

- `Animal` — животное, инкапсулирует атрибуты и поведение.
- `Enclosure` — вольер с типами животных, вместимостью.
- `FeedingSchedule` — график кормлений, связанный с животными.

### События домена (Domain Events)

- `AnimalMovedEvent` — перемещение животного.
- `FeedingScheduleCreatedEvent` — добавление графика кормления.

---

## Clean Architecture

### Архитектурные слои

1. **Domain (Доменный слой)**
    - Основная бизнес-логика: `Animal`, `Enclosure`, `FeedingSchedule`
    - Не зависит ни от каких других слоев

2. **Application (Сервисный слой)**
    - Координирует действия между сущностями
    - Примеры: `AnimalMovementService`, `FeedingOrganizationService`, `ZooStatisticService`
    - Зависит только от доменного слоя

3. **Infrastructure (Инфраструктура)**
    - Реализация репозиториев, работа с базами данных
    - DTO, запросы, респонсы: `CreateAnimalRequest`, `AnimalResponse` и т.д.
    - Имплементация интерфейсов: `AnimalRepositoryImpl`, `EnclosureRepositoryImpl`
    - Конвертеры: `AnimalConverter`, `EnclosureConverter`, `FeedingScheduleConverter`
    - Зависит от Application и Domain

4. **Interface (Веб / API слой)**
    - Контроллеры: `AnimalController`, `EnclosureController`, `FeedingScheduleController`
    - Зависит только от Application и DTO

---
