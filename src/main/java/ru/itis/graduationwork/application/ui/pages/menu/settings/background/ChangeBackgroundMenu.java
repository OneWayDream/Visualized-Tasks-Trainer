package ru.itis.graduationwork.application.ui.pages.menu.settings.background;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Menu;

public class ChangeBackgroundMenu extends Menu {

    @Override
    protected void initMenu() {
        menu.setText(LocalizationManager.getLocaleTextByKey("menu.settings.change-background.text"));
        menu.setIcon(getImageIcon(Image.BACKGROUND));
        menu.setForeground(ColorsManager.getTextColor());
        addMenuItems();
    }

    private void addMenuItems(){
        menu.add(new SetBackgroundImageMenuItem().getComponent());
        menu.add(new ResetBackgroundImageMenuItem().getComponent());
    }
}
