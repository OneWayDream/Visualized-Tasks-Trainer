package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class LocalizationManagerTests {

    @Test
    public void get_en_locale_message(){
        LocalizationManager.setLocale(Locale.EN);
        Assertions.assertEquals("Failed to create a new file.",
                LocalizationManager.getLocaleTextByKey("exceptions.file-creation-exception.message"));
    }

    @Test
    public void get_ru_locale_message(){
        LocalizationManager.setLocale(Locale.RU);
        Assertions.assertEquals("Не удалось создать новый файл.",
                LocalizationManager.getLocaleTextByKey("exceptions.file-creation-exception.message"));
    }

}
