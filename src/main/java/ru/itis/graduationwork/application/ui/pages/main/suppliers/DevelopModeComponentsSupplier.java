package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.entities.RecentList;
import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.managers.content.RecentManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.buttons.*;
import ru.itis.graduationwork.application.ui.pages.main.dialogs.recent.OpenRecentListButton;
import ru.itis.graduationwork.application.ui.pages.main.dialogs.recent.RecentListDialog;

import javax.swing.*;
import java.util.List;

public class DevelopModeComponentsSupplier extends ModeComponentsSupplier {

    @Override
    public ImageIcon getLeftPanelTitleIcon() {
        return IconsManager.getImageIcon(Image.DEVELOPER,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_LABEL_HEIGHT);
    }

    @Override
    public ImageIcon getRightPanelTitleIcon() {
        return IconsManager.getImageIcon(Image.DEVELOP,
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
    public List<JComponent> getRecentPageContent(RecentListDialog recentListDialog) {
        RecentList list = RecentManager.getRecentProjects();
        return recentListToComponents(list, recentListDialog);
    }

    @Override
    public String getEmptyRecentListText() {
        return LocalizationManager.getLocaleTextByKey("main-frame.recent-list.develop.empty-list");
    }

}
