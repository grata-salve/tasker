package com.example.tasker.domain.constants;

public enum Action {
  CREATED,    // Создание новой задачи или тикета
  UPDATED,    // Обновление существующей задачи или тикета
  DELETED,    // Удаление задачи или тикета
  ASSIGNED,    // Назначение задачи или тикета пользователю
  UNASSIGNED,
  RESOLVED,   // Решение проблемы
  CLOSED,     // Закрытие задачи или тикета
  REOPENED,    // Переоткрытие закрытой задачи или тикета
  COMMENTED,    // Добавление комментария к задаче или тикету
  ATTACHED,
  DETACHED
}
