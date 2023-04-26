package ru.itis.visualtasks.desktopapp.application.ui.pages.menu.account.upload;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.server.TaskUploadManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.MenuItem;
import ru.itis.visualtasks.desktopapp.exceptions.server.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TaskUploadMenuItem extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuItem.setText(LocalizationManager.getLocaleTextByKey("menu.account.upload.text"));
        menuItem.setIcon(getImageIcon(Image.UPLOAD));
        menuItem.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            TaskUploadManager.uploadCurrentTask();
            JOptionPane.showMessageDialog(
                    Application.getCurrentPageFrame().getComponent(),
                    LocalizationManager.getLocaleTextByKey("menu.account.upload.success-text"),
                    "", JOptionPane.INFORMATION_MESSAGE);
        } catch (SessionExpiredException | AuthenticationRequestExecutionException |
                 ConfigIsIncompleteException | ArchiveCreationException |
                 TaskUploadException | ConfigInteractionException | TaskArchiveReadingException |
                 ApiDifferenceException | TaskUploadRequestExecutionException exception){
            exception.handle();
        }
    }

}
