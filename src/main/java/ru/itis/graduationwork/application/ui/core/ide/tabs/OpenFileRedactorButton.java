package ru.itis.graduationwork.application.ui.core.ide.tabs;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.core.ide.workspace.ContentTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenFileRedactorButton extends Button {

    public OpenFileRedactorButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
                ColorsManager.getButtonBordersColor()));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.IDE,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_WIDTH,
                IdeFramesIconsConstants.TAB_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-code-ide-button.text"));
        button.setToolTipText(LocalizationManager.getLocaleTextByKey("ide.content.main-buttons.open-code-ide-button.tooltip-text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(10);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStudyMode() && isEditorFilePathIsNull()){
            WorkspaceContentManager.setEditorContent(ProjectFilesManager.getSolutionFilePath(
                    ConfigManager.getProjectPath(),
                    ConfigManager.getProjectLanguage()
            ));
        } else {
            WorkspaceContentManager.changeWorkspaceContent(ContentTab.FILE_EDITOR);
        }
    }

    private boolean isStudyMode(){
        return Application.getMode() == Mode.STUDY;
    }

    private boolean isEditorFilePathIsNull(){
        return WorkspaceContentManager.getEditorFilePath() == null;
    }
}
