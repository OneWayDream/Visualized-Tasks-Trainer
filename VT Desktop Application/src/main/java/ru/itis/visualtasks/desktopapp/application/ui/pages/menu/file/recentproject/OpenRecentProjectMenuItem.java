package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.file.recentproject;

import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.RecentListDialog;

import java.awt.event.ActionEvent;

public class OpenRecentProjectMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.file.open-recent-project.text"));
        menuItem.setForeground(ColorsManager.getTextColor());
        menuItem.setIcon(getImageIcon(Image.LIST));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RecentListDialog recentListDialog = new RecentListDialog();
        recentListDialog.initDialog();
    }

}
