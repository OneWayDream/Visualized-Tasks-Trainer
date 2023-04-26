package ru.itis.visualtasks.backendserver.dto.search;

import lombok.Getter;
import ru.itis.visualtasks.backendserver.exceptions.search.UnsupportedLanguageException;

import java.util.Arrays;
import java.util.List;

public enum Language {

    JAVA("JAVA", "Java"),
    PYTHON("PYTHON", "Python");

    @Getter
    private final String languageKey;
    @Getter
    private final String languageName;

    Language(String languageKey, String languageName) {
        this.languageKey = languageKey;
        this.languageName = languageName;
    }

    public static Language getByLanguageKey(String languageKey){
        return Arrays.stream(values())
                .filter(language -> language.getLanguageKey().equals(languageKey))
                .findFirst()
                .orElseThrow(UnsupportedLanguageException::new);
    }

    public static List<Language> getByLanguageKeys(List<String> languageKeys){
        return languageKeys.stream()
                .map(Language::getByLanguageKey)
                .toList();
    }

    public static List<Language> getAll(){
        return Arrays.asList(values());
    }

}
