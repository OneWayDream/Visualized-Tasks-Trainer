package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;

import javax.swing.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class LookAndFeelManagerTests {

    @Test
    public void init_dark_look_and_feel(){
        SettingsManager.setTheme(Theme.DARK);
        LookAndFeelManager.initLookAndFeel();
        Assertions.assertEquals("FlatLaf Darcula", UIManager.getLookAndFeel().getName());
    }

    @Test
    public void init_light_look_and_feel(){
        SettingsManager.setTheme(Theme.LIGHT);
        LookAndFeelManager.initLookAndFeel();
        Assertions.assertEquals("Arc Theme - Orange", UIManager.getLookAndFeel().getName());
    }

}
