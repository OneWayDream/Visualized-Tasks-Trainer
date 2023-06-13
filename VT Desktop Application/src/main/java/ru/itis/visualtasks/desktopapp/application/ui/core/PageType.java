package ru.itis.visualtasks.desktopapp.application.ui.core;

public enum PageType {

    MAIN("main"),
    DEVELOP("develop"),
    STUDY("study");

    private final String value;

    PageType (String key) {
        this.value = key;
    }

    public String getValue(){
        return value;
    }

}
