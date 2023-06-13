package ru.itis.visualtasks.desktopapp.application.entities.project;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedLanguageException;

import java.util.Arrays;

public enum Language {

    JAVA("Java", ".java"),
    PYTHON("Python", ".py");

    @Getter
    private final String name;
    @Getter
    private final String extension;

    Language(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public static String[] getLanguagesNames(){
        return Arrays.stream(values())
                .map(Language::getName)
                .toList().toArray(new String[0]);
    }

    public static Language getByName(String name){
        return Arrays.stream(values())
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElseThrow(UnsupportedLanguageException::new);
    }

}
