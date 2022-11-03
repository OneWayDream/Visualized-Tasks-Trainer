package ru.itis.graduationwork.application;

import lombok.Getter;
import ru.itis.graduationwork.application.managers.LookAndFeelManager;
import ru.itis.graduationwork.application.managers.PagesManager;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.units.Locale;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;

public class Application {

    @Getter
    private static PageFrame currentPageFrame;
    private static PageType currentPageType;

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
    }

    private void init(){
        LookAndFeelManager.initLookAndFeel();
        initStartPage();
    }

    private static void initStartPage(){
        currentPageType = PagesManager.getStartPageType();
        currentPageFrame = PagesManager.getPage(currentPageType);
        currentPageFrame.initPage();
    }

    public static void changeTheme(Theme theme){
        SettingsManager.setTheme(theme);
        LookAndFeelManager.initLookAndFeel();
        reloadPage();
    }

    public static void changeLocale(Locale locale){
        SettingsManager.setLocale(locale);
        reloadPage();
    }

    private static void reloadPage(){
        currentPageFrame.dispose();
        currentPageFrame = PagesManager.getPage(currentPageType);
        currentPageFrame.initPage();
    }

    public static void exit(){
        SettingsManager.saveSettings();
        System.exit(0);
    }

}
