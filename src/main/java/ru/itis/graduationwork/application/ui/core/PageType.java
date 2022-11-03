package ru.itis.graduationwork.application.ui.core;

public enum PageType {

    MAIN("main");

    private final String value;

    PageType (String key) {
        this.value = key;
    }

    public String getValue(){
        return value;
    }

}
