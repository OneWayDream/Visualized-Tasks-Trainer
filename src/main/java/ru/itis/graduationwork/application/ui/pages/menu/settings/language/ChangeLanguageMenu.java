package ru.itis.graduationwork.application.ui.pages.menu.settings.language;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Menu;

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
