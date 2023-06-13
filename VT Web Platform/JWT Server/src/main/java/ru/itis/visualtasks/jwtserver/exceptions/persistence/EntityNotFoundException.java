package ru.itis.visualtasks.jwtserver.exceptions.persistence;

import jakarta.persistence.PersistenceException;

public class EntityNotFoundException extends PersistenceException {

    public EntityNotFoundException() {
        super();
    }

}
