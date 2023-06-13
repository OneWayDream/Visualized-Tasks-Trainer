package ru.itis.visualtasks.tasksserver.exceptions.persistence;

import jakarta.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
