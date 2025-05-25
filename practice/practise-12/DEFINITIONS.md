# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Как в проекте происходит версионирование? Как его подключить?
  При помощи Liquibase, и файла с указанием миграций
  db/changelog/db.changelog-master.yaml
  потом туда пишутся миграции, что позволяет при запуске приложения обновить бд если нужно
- Как в системе версионирования создать таблицу? Как создать внешний ключ?
```shell
databaseChangeLog:
  - changeSet:
      id: 1
      author: yourname
      changes:
        - createTable:
            tableName: users
            columns:
              - column:
                  name: id
                  type: bigint
                  autoIncrement: true
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: username
                  type: varchar(255)
                  constraints:
                    nullable: false
              - column:
                  name: email
                  type: varchar(255)
                  constraints:
                    nullable: false
```

```shell
databaseChangeLog:
  - changeSet:
      id: 2
      author: yourname
      changes:
        - addColumn:
            tableName: orders
            columns:
              - column:
                  name: user_id
                  type: bigint
                  constraints:
                    nullable: false

        - addForeignKeyConstraint:
            baseTableName: orders
            baseColumnNames: user_id
            referencedTableName: users
            referencedColumnNames: id
            constraintName: fk_orders_users
```
- Как поднять приложение в докере
```shell
docker-compose up --build
```
перед этим содать dockerfile и docker-compose
- Что значит параметр ddl-auto? какие у него есть параметры?
  автоматическое правление схемой бд
1) none - никаких изменений
2) validate - схема бд совпадает с сущностями
3) update - автоматически обновлять схему бд
4) create - создавать заново при запуске
5) create-drop - создавать заново при запуске + удалять при выходе
- Как сделать связь один ко многим?
```java
 @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
```
- +1 уникальный факт связанный с темами выше или семинаром
  если запускать через -d то нельзя понять ошибку если она есть(попе было больно), лучше запускать а потом выходить через ^ + c