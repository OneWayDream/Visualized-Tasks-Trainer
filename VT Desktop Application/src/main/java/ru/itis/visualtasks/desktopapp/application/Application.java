package ru.itis.visualtasks.desktopapp.application;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.itis.visualtasks.desktopapp.application.managers.project.WorkspaceContentManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain.VisualizationControlButtonsDisableReasonManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LookAndFeelManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.PagesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.SettingsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Locale;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PageFrame;

@Slf4j
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
        log.info(LocalizationManager.getLocaleTextByKey("logging.application-start"));
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
        VisualizationControlButtonsDisableReasonManager.changeLocale();
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
        WorkspaceContentManager.saveEditorChangedIfNeeded();
        log.info(LocalizationManager.getLocaleTextByKey("logging.application-close"));
        System.exit(0);
    }

}
