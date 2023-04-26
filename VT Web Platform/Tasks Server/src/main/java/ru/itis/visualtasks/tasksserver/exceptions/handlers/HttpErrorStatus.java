package ru.itis.visualtasks.tasksserver.exceptions.handlers;

public enum HttpErrorStatus {

    BANNED_TOKEN(452),
    EXPIRED_TOKEN(453),
    INCORRECT_TOKEN(454),
    ACCESS_DENIED(455),
    UNSUPPORTED_LANGUAGE_ERROR(458),
    UNSUPPORTED_EXTENSION_ERROR(459),
    UNSUPPORTED_VISUALIZATION_TYPE_ERROR(460),
    TASK_READING_ERROR(461),
    CONFIG_READING_ERROR(462),
    CONFIG_NOT_FOUND_ERROR(463),
    INCOMPLETE_CONFIG_ERROR(464);

    private final int value;

    HttpErrorStatus(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

}
