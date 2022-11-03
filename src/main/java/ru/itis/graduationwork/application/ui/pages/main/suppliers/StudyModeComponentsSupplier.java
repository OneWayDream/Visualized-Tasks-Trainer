package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.settings.entities.RecentList;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.buttons.*;
import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.RecentManager;

import javax.swing.*;
import java.util.List;

public class StudyModeComponentsSupplier extends ModeComponentsSupplier {

    @Override
    public ImageIcon getLeftPanelTitleIcon() {
        return getImageIcon(Image.STUDENT,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public ImageIcon getRightPanelTitleIcon() {
        return getImageIcon(Image.STUDY,
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
    public List<Button> getRecentPageButtons() {
        RecentList list = RecentManager.getRecentTasks();
        return list.getContent().entrySet().stream()
                .map(entry -> (Button) new RecentTaskButton(entry.getKey(), entry.getValue()))
                .toList();
    }

}
