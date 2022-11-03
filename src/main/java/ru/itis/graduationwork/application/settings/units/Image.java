package ru.itis.graduationwork.application.settings.units;

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
    ARROW_RIGHT("arrow-right");

    private final String key;

    Image(String key) {
        this.key = key;
    }

    public String getKey(){
        return key;
    }

}
