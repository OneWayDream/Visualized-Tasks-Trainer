package ru.itis.visualtasks.tasksserver.exceptions.tasks;

public abstract class TaskException extends RuntimeException {

    public TaskException() {
        super();
    }

    public TaskException(Throwable cause) {
        super(cause);
    }
}
