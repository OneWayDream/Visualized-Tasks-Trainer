package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.language;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Menu;

public class ChangeLanguageMenu extends Menu {

    public ChangeLanguageMenu(){
        super();
    }

    @Override
    protected void initMenu() {
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-language.text"));
        menu.setForeground(ColorsManager.getTextColor());
        menu.setIcon(getImageIcon(Image.LANGUAGE));
        addMenuItems();
    }

    private void addMenuItems(){
        menu.add(new EnglishLanguageMenuItem().getComponent());
        menu.add(new RussianLanguageMenuItem().getComponent());
    }

}
