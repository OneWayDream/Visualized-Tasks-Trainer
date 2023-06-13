package ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons;

import lombok.Setter;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.RecentManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.RecentListDialog;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectDirectoryNotExistsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecentProjectButton extends Button {

    private final String name;
    private final String path;
    @Setter
    private RecentListDialog recentListDialog;

    public RecentProjectButton(String name, String path){
        super();
        this.name = name;
        this.path = path;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(900, 50));
        button.setBorder(BorderFactory.createLineBorder(ColorsManager.getButtonBordersColor(), 2));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.ARROW_RIGHT,
                MainFrameIconsConstants.RECENT_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.RECENT_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(name + " (" + path + ")");
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setForeground(ColorsManager.getTextColor());
        button.setHorizontalTextPosition(SwingConstants.LEFT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            ProjectsManager.openProject(path);
        } catch (ProjectDirectoryNotExistsException exception){
            exception.handle();
            if (Application.getMode() == Mode.DEVELOP){
                RecentManager.deleteRecentProject(path);
            } else {
                RecentManager.deleteRecentTask(path);
            }
            recentListDialog.reload();
        }
    }

}
