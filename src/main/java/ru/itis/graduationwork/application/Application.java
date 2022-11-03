package ru.itis.graduationwork.application;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import lombok.Getter;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.units.Locale;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Application {

    @Getter
    private static PageFrame currentPageFrame;

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
    }

    private void init(){
        initLookAndFeel();
        initStartPage();
    }

    private static void initLookAndFeel(){
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

    private static void initStartPage(){
        currentPageFrame = new MainPageFrame();
        currentPageFrame.initPage();
    }

    public static void changeTheme(Theme theme){
        SettingsManager.setTheme(theme);
        initLookAndFeel();
        reloadPage();
    }

    public static void changeLocale(Locale locale){
        SettingsManager.setLocale(locale);
        reloadPage();
    }

    private static void reloadPage(){
        currentPageFrame.dispose();
        initStartPage();
    }

    public static void exit(){
        SettingsManager.saveSettings();
        System.exit(0);
    }

}
