package ru.itis.visualtasks.backendserver.exceptions.persistence;

import jakarta.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
