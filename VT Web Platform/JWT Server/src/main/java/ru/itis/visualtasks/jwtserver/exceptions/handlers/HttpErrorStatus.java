package ru.itis.visualtasks.jwtserver.exceptions.handlers;


public enum HttpErrorStatus {

    BANNED_TOKEN(452),
    EXPIRED_TOKEN(453),
    INCORRECT_TOKEN(454),
    ACCESS_DENIED(455),
    BANNED_USER(456),
    INCORRECT_USER_DATA(457);

    private final int value;

    HttpErrorStatus(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

}
