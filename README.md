# ToDo List Application

Учебное веб-приложение для управления списком задач. Можно добавлять задачи, отмечать их выполненными, удалять записи и фильтровать список по статусу.

## Возможности

- Добавление задачи
- Отметка задачи как выполненной
- Удаление задачи
- Просмотр всех, активных или выполненных задач
- Счётчики активных и выполненных задач
- Сохранение данных в PostgreSQL

## Технологии

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA и Hibernate
- PostgreSQL
- Thymeleaf
- Maven

## Требования

- JDK 17 или новее
- PostgreSQL

## Запуск

1. Склонируйте репозиторий и перейдите в папку проекта:

   ```bash
   git clone https://github.com/ValZ1/ToDoListApplication.git
   cd ToDoListApplication
   ```

2. Создайте базу данных PostgreSQL. В текущей конфигурации приложения используется база `admin`:

   ```sql
   CREATE DATABASE admin;
   ```

3. Укажите URL базы данных, имя пользователя и пароль в `src/main/resources/application.properties`, чтобы они соответствовали вашей локальной настройке PostgreSQL. Не добавляйте реальные пароли и другие секреты в публичный репозиторий.

4. Запустите приложение из корня проекта:

   **Windows PowerShell**

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **macOS / Linux**

   ```bash
   ./mvnw spring-boot:run
   ```

5. Откройте в браузере [http://localhost:8080](http://localhost:8080).

Таблица задач создаётся и обновляется Hibernate при запуске приложения.

## Структура приложения

- `controller` — обработка HTTP-запросов и отображение страницы
- `service` — операции с задачами, фильтрация и подсчёт статистики
- `dao` — доступ к данным через JPA `EntityManager`
- `entity` — сущность задачи и её статусы
- `entity/dto` — объект для передачи списка задач и статистики в контроллер
- `resources/templates` — HTML-шаблон Thymeleaf
- `resources/static` — таблицы стилей

## Основные маршруты

| Метод | Адрес | Назначение |
| --- | --- | --- |
| `GET` | `/` | Перенаправляет на главную страницу |
| `GET` | `/home` | Показывает задачи; параметр `filter` принимает `active`, `done` или `all` |
| `POST` | `/add-record` | Добавляет задачу |
| `POST` | `/update-record` | Отмечает задачу выполненной |
| `POST` | `/delete-record` | Удаляет задачу |
