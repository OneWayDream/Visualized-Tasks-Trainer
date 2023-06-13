package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdePageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.background.ChangeBackgroundMenu;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.language.ChangeLanguageMenu;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.theme.ChangeThemeMenu;

public class SettingsMenu extends Menu {

    public SettingsMenu(){
        super();
    }

    @Override
    protected void initMenu() {
        setMenuStyle();
        addGeneralSettings();
        if (isIdePageFrame()){
            addIdeSettings();
        }
    }

    private void addGeneralSettings(){
        menu.add(new ChangeThemeMenu().getComponent());
        menu.add(new ChangeLanguageMenu().getComponent());
    }

    private boolean isIdePageFrame(){
        return Application.getCurrentPageFrame() instanceof IdePageFrame;
    }

    private void addIdeSettings(){
        menu.add(new ChangeBackgroundMenu().getComponent());
    }

    private void setMenuStyle(){
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon(Image.SETTINGS));
    }

}
