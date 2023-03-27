package ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers;

import ru.itis.visualtasks.desktopapp.application.entities.project.RecentList;
import ru.itis.visualtasks.desktopapp.application.managers.content.IconsManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.application.managers.content.RecentManager;
import ru.itis.visualtasks.desktopapp.application.settings.Image;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons.OpenFolderAsAProjectButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons.OpenGuideButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons.OpenSiteButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons.OpenTasksListButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.OpenRecentListButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.RecentListDialog;

import javax.swing.*;
import java.util.List;

public class StudyModeComponentsSupplier extends ModeComponentsSupplier {

    @Override
    public ImageIcon getLeftPanelTitleIcon() {
        return IconsManager.getImageIcon(Image.STUDENT,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public ImageIcon getRightPanelTitleIcon() {
        return IconsManager.getImageIcon(Image.STUDY,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public String getLeftPanelIconTitle() {
        return LocalizationManager.getLocaleTextByKey("main-frame.left-panel.label.study.title");
    }

    @Override
    public String getLeftPanelIconDescription() {
        return LocalizationManager.getLocaleTextByKey("main-frame.left-panel.label.study.description");
    }

    @Override
    public List<Button> getLeftPanelButtons() {
        return List.of(
                new OpenRecentListButton(),
                new OpenFolderAsAProjectButton()
        );
    }

    @Override
    public List<Button> getRightPanelButtons() {
        return List.of(
                new OpenSiteButton(),
                new OpenGuideButton(),
                new OpenTasksListButton()
        );
    }

    @Override
    public String getRecentDialogTitle() {
        return LocalizationManager.getLocaleTextByKey("main-frame.recent-list.study.title");
    }

    @Override
    public List<JComponent> getRecentPageContent(RecentListDialog recentListDialog) {
        RecentList list = RecentManager.getRecentTasks();
        return recentListToComponents(list, recentListDialog);
    }

    @Override
    public String getEmptyRecentListText() {
        return LocalizationManager.getLocaleTextByKey("main-frame.recent-list.study.empty-list");
    }

}
