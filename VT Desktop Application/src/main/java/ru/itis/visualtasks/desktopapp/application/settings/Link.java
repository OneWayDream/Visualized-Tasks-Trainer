package ru.itis.visualtasks.desktopapp.application.settings;

public enum Link {

    DOCUMENTATION("documentation-url"),
    GUIDE("guide-url"),
    SITE("site-url"),
    TASKS("tasks-url"),
    PLUGINS("plugins-url");

    private final String key;

    Link(String key) {
        this.key = key;
    }

    public String getKey(){
        return key;
    }

}
