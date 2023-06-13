package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.loaders.SettingsLoader;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.settings.UserSettings;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class SettingsManagerTests {

    @BeforeAll
    public static void beforeAll(){
        SettingsManager.setLocale(Locale.EN);
        SettingsManager.setTheme(Theme.DARK);
        SettingsManager.setMode(Mode.DEVELOP);
        SettingsManager.setBackgroundImageName("background-image.jpg");
    }

    @Test
    @Order(1)
    public void get_locale(){
        Assertions.assertEquals(Locale.EN, SettingsManager.getLocale());
    }

    @Test
    @Order(2)
    public void set_locale(){
        SettingsManager.setLocale(Locale.RU);
        Assertions.assertEquals(Locale.RU, SettingsManager.getLocale());
    }

    @Test
    @Order(3)
    public void get_theme(){
        Assertions.assertEquals(Theme.DARK, SettingsManager.getTheme());
    }

    @Test
    @Order(4)
    public void set_theme(){
        SettingsManager.setTheme(Theme.LIGHT);
        Assertions.assertEquals(Theme.LIGHT, SettingsManager.getTheme());
    }

    @Test
    @Order(5)
    public void get_mode(){
        Assertions.assertEquals(Mode.DEVELOP, SettingsManager.getMode());
    }

    @Test
    @Order(6)
    public void set_mode(){
        SettingsManager.setMode(Mode.STUDY);
        Assertions.assertEquals(Mode.STUDY, SettingsManager.getMode());
    }

    @Test
    @Order(7)
    public void get_background_image_name(){
        Assertions.assertEquals("background-image.jpg", SettingsManager.getBackgroundImageName());
    }

    @Test
    @Order(8)
    public void set_background_image_name(){
        SettingsManager.setBackgroundImageName("new-background-image.jpg");
        Assertions.assertEquals("new-background-image.jpg", SettingsManager.getBackgroundImageName());
    }

    @Test
    @Order(9)
    public void save_settings(){
        SettingsManager.saveSettings();
        UserSettings settings = SettingsLoader.getUserSettings();
        Assertions.assertEquals(Locale.RU, settings.getLocale());
        Assertions.assertEquals(Theme.LIGHT, settings.getTheme());
        Assertions.assertEquals(Mode.STUDY, settings.getMode());
        Assertions.assertEquals("new-background-image.jpg", settings.getBackgroundImageName());
    }

    @AfterAll
    public static void afterAll(){
        SettingsManager.setLocale(Locale.EN);
        SettingsManager.setTheme(Theme.DARK);
        SettingsManager.setMode(Mode.DEVELOP);
        SettingsManager.setBackgroundImageName("background-image.jpg");
        SettingsManager.saveSettings();
    }

}
