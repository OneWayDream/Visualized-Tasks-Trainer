package ru.itis.graduationwork.application.ui.pages.menu.settings;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.templates.Menu;
import ru.itis.graduationwork.application.ui.pages.menu.settings.background.ChangeBackgroundMenu;
import ru.itis.graduationwork.application.ui.pages.menu.settings.language.ChangeLanguageMenu;
import ru.itis.graduationwork.application.ui.pages.menu.settings.theme.ChangeThemeMenu;

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
