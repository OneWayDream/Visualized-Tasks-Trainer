package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.content.IconsManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.ui.core.ide.IdeFramesIconsConstants;
import ru.itis.graduationwork.application.ui.core.templates.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChooseFileButton extends Button {


    public ChooseFileButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
                ColorsManager.getButtonBordersColor()));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(400, 100));
        button.setMaximumSize(new Dimension(400, 100));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        button.setIcon(IconsManager.getImageIcon(Image.FILE,
                IdeFramesIconsConstants.FILE_CHOOSE_BUTTONS_ICON_WIGHT,
                IdeFramesIconsConstants.FILE_CHOOSE_BUTTONS_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("ide.content.workspace.choose-content-file-panel.choose-file-button.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(20);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ContentFileChooser chooser = new ContentFileChooser();
        chooser.execute();
    }

}
