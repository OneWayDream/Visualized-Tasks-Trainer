package ru.itis.graduationwork.application.ui.pages.main.buttons;

import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.choosers.DirectoryAsProjectChooser;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.LocalizationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenFolderAsAProjectButton extends Button {

    public OpenFolderAsAProjectButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(500, 90));
        button.setBorder(BorderFactory.createMatteBorder(2, 15, 2, 2,
                ColorsManager.getButtonBordersColor()));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        button.setIcon(supplier.getImageIcon(Image.FOLDER,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.left-panel.buttons.open-folder-as-project.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Open the folder as a project..");
        DirectoryAsProjectChooser chooser = new DirectoryAsProjectChooser();
        chooser.execute();
    }

}
