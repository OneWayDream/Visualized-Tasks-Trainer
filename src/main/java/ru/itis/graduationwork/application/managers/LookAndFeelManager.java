package ru.itis.graduationwork.application.managers;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.settings.units.Theme;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class LookAndFeelManager {

    public static void initLookAndFeel(){
        if (SettingsManager.getTheme() == Theme.DARK){
            initDarkLookAndFeel();
        } else {
            initLightLookAndFeel();
        }
    }

    private static void initDarkLookAndFeel(){
        FlatDarculaLaf.setup();
        try{
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    private static void initLightLookAndFeel(){
        IntelliJTheme.setup(Application.class.getClassLoader().getResourceAsStream("themes/arc-theme-orange.theme.json"));
        try{
            FlatLaf lightFlatLaf = IntelliJTheme.createLaf(Objects.requireNonNull(
                    Application.class.getClassLoader().getResourceAsStream("themes/arc-theme-orange.theme.json")));
            UIManager.setLookAndFeel(lightFlatLaf);
        } catch (UnsupportedLookAndFeelException | IOException e) {
            System.err.println("Failed to initialize LaF");
        }
    }

}
