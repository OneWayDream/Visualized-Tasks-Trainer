package ru.itis.visualtasks.desktopapp.application.loaders;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.settings.UserSettings;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(1)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class SettingsLoaderTests {

    @Test
    public void load_user_settings(){
        UserSettings userSettings = SettingsLoader.getUserSettings();
        Assertions.assertEquals("background-image.jpg", userSettings.getBackgroundImageName());
    }

    @Test
    public void save_user_settings(){
        UserSettings userSettings = SettingsLoader.getUserSettings();
        userSettings.setBackgroundImageName("new-background-image.jpg");
        SettingsLoader.saveUserSettings(userSettings);
        userSettings = SettingsLoader.getUserSettings();
        Assertions.assertEquals("new-background-image.jpg",
                userSettings.getBackgroundImageName());
        resetUserSettings();
    }

    private void resetUserSettings() {
        UserSettings userSettings = SettingsLoader.getUserSettings();
        userSettings.setBackgroundImageName("background-image.jpg");
        SettingsLoader.saveUserSettings(userSettings);
    }

    @Test
    public void get_default_user_settings(){
        UserSettings defaultUserSettings = SettingsLoader.getDefaultUserSettings();
        Assertions.assertEquals(defaultUserSettings.getLocale(), Locale.EN);
        Assertions.assertEquals(defaultUserSettings.getMode(), Mode.DEVELOP);
        Assertions.assertEquals(defaultUserSettings.getTheme(), Theme.DARK);
    }

}
