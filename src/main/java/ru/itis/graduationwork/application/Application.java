package ru.itis.graduationwork.application;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import lombok.Getter;
import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.application.settings.UserSettings;
import ru.itis.graduationwork.application.ui.core.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;
import ru.itis.graduationwork.application.utils.ColorsManager;
import ru.itis.graduationwork.application.utils.ConfigurationsManager;
import ru.itis.graduationwork.application.utils.ImagesManager;
import ru.itis.graduationwork.application.utils.LocalizationManager;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Application {
    @Getter
    private static PageFrame currentPageFrame;
    @Getter
    private static UserSettings settings;

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
    }

    private void init(){
        initUserSettings();
        initLookAndFeel();
        initMainPage();
    }

    private void initUserSettings(){
        settings = ConfigurationsManager.getUserSettings();
        setManagersTheme();
        setManagersLocale();
    }

    private static void initLookAndFeel(){
        if (settings.getTheme() == Theme.DARK){
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

    private static void initMainPage(){
        currentPageFrame = new MainPageFrame();
        currentPageFrame.initPage();
    }

    public static void changeTheme(Theme theme){
        settings.setTheme(theme);
        setManagersTheme();
        initLookAndFeel();
        reloadPage();
    }

    public static void changeLocale(Locale locale){
        settings.setLocale(locale);
        setManagersLocale();
        reloadPage();
    }

    private static void reloadPage(){
        currentPageFrame.dispose();
        initMainPage();
    }

    private static void setManagersTheme(){
        ImagesManager.setTheme(settings.getTheme());
        ColorsManager.setTheme(settings.getTheme());
    }

    private static void setManagersLocale(){
        LocalizationManager.setLocale(settings.getLocale());
    }

    public static void close(){
        ConfigurationsManager.saveUserSettings(settings);
    }

}
