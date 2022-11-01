package ru.itis.graduationwork.application.ui.pages.main.suppliers;

import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.buttons.*;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.utils.LocalizationManager;

import javax.swing.*;
import java.util.List;

public class StudyComponentsSupplier extends ComponentsSupplier{

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
                new OpenRecentProjectButton(),
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

}
