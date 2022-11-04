package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.settings.entities.RecentList;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.buttons.*;
import ru.itis.graduationwork.application.managers.LocalizationManager;
import ru.itis.graduationwork.application.managers.RecentManager;
import ru.itis.graduationwork.application.ui.pages.main.dialogs.recent.OpenRecentListButton;

import javax.swing.*;
import java.util.List;

public class DevelopModeComponentsSupplier extends ModeComponentsSupplier {

    @Override
    public ImageIcon getLeftPanelTitleIcon() {
        return getImageIcon(Image.DEVELOPER,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public ImageIcon getRightPanelTitleIcon() {
        return getImageIcon(Image.DEVELOP,
                MainFrameIconsConstants.RIGHT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.RIGHT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public String getLeftPanelIconTitle() {
        return LocalizationManager.getLocaleTextByKey("main-frame.left-panel.label.develop.title");
    }

    @Override
    public String getLeftPanelIconDescription() {
        return LocalizationManager.getLocaleTextByKey("main-frame.left-panel.label.develop.description");
    }

    @Override
    public List<Button> getLeftPanelButtons() {
        return List.of(
                new CreateNewProjectButton(),
                new OpenRecentListButton(),
                new OpenFolderAsAProjectButton()
        );
    }

    @Override
    public List<Button> getRightPanelButtons() {
        return List.of(
                new OpenSiteButton(),
                new OpenDocumentationButton(),
                new OpenPluginsButton()
        );
    }

    @Override
    public String getRecentDialogTitle() {
        return LocalizationManager.getLocaleTextByKey("main-frame.recent-list.develop.title");
    }

    @Override
    public List<Button> getRecentPageButtons() {
        RecentList list = RecentManager.getRecentProjects();
        System.out.println(list);
        return list.getContent().entrySet().stream()
                .map(entry -> (Button) new RecentProjectButton(entry.getKey(), entry.getValue()))
                .toList();
    }

}
