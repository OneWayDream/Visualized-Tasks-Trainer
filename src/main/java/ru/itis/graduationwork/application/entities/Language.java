package ru.itis.graduationwork.application.entities;

import ru.itis.graduationwork.exceptions.UnsupportedLanguageException;

import java.util.Arrays;

public enum Language {

    JAVA("Java", ".java");

    private final String name;
    private final String extension;

    Language(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
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
