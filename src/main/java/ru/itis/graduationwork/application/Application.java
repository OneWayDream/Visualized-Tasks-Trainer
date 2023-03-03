package ru.itis.graduationwork.application;

import lombok.Getter;
import ru.itis.graduationwork.application.managers.LookAndFeelManager;
import ru.itis.graduationwork.application.managers.PagesManager;
import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.settings.Locale;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.settings.Theme;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;

public class Application {

    @Getter
    private static PageFrame currentPageFrame;
    @Getter
    private static Mode mode;

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
    }

    private void init(){
        LookAndFeelManager.initLookAndFeel();
        initMode();
        initStartPage();
    }

    private void initMode(){
        mode = SettingsManager.getMode();
    }

    private static void initStartPage(){
        currentPageFrame = PagesManager.getPage(PagesManager.getStartPageType());
        currentPageFrame.initPage();
    }

    public static void changeMode(Mode mode){
        Application.mode = mode;
        SettingsManager.setMode(mode);
        currentPageFrame.changeMode(mode);
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

    public static void reloadPage(){
        currentPageFrame.dispose();
        currentPageFrame = currentPageFrame.copy();
        currentPageFrame.initPage();
    }

    public static void changePage(PageFrame pageFrame){
        currentPageFrame.dispose();
        currentPageFrame = pageFrame;
        currentPageFrame.initPage();
    }

    public static void exit(){
        SettingsManager.saveSettings();
        System.exit(0);
    }

}
