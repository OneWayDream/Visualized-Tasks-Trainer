package ru.itis.visualtasks.desktopapp.application.settings;

public enum Image {

    PLUS("plus"),
    DEVELOPER("developer"),
    DEVELOP("develop"),
    STUDENT("student"),
    STUDY("study"),
    RECENT("recent"),
    FOLDER("folder"),
    DOWNLOAD("download"),
    SWITCH("switch"),
    SITE("site"),
    DOCUMENTATION("documentation"),
    GUIDE("guide"),
    PLUGIN("plugin"),
    TASKS("tasks"),
    SETTINGS("settings"),
    LIGHT_THEME("light-theme"),
    DARK_THEME("dark-theme"),
    THEME("theme"),
    LANGUAGE("language"),
    RUSSIA("russia"),
    UK("uk"),
    ARROW_RIGHT("arrow-right"),
    FILE("file"),
    NEW_PROJECT("new-project"),
    LIST("list"),
    MENU("menu"),
    BACKGROUND("background"),
    IMAGE("image"),
    RESET("reset"),
    CONTENT("content"),
    IDE("ide"),
    TERMS("terms"),
    GENERAL("general"),
    PAUSE("pause"),
    PLAY("play"),
    NEXT("next"),
    PREVIOUS("previous"),
    AT_START("at-start"),
    AT_END("at-end"),
    EDIT("edit"),
    RUN("run");

    private final String key;

    Image(String key) {
        this.key = key;
    }

    public String getKey(){
        return key;
    }

}
