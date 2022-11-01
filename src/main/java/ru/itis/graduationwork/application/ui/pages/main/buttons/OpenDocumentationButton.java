package ru.itis.graduationwork.application.ui.pages.main.buttons;

import ru.itis.graduationwork.exceptions.application.UrlBrowsingException;
import ru.itis.graduationwork.application.settings.Image;
import ru.itis.graduationwork.application.settings.Link;
import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ComponentsSupplier;
import ru.itis.graduationwork.application.utils.ColorsManager;
import ru.itis.graduationwork.application.utils.LinksManager;
import ru.itis.graduationwork.application.utils.LocalizationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OpenDocumentationButton extends Button {

    public OpenDocumentationButton(){
        super();
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(200, 200));
        button.setBorder(BorderFactory.createLineBorder(ColorsManager.getButtonBordersColor(), 3));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        ComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        button.setIcon(supplier.getImageIcon(Image.DOCUMENTATION,
                MainFrameIconsConstants.RIGHT_PANEL_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.RIGHT_PANEL_BUTTON_ICON_HEIGHT));
    }

    private void setTextStyle(){
        button.setText(LocalizationManager.getLocaleTextByKey("main-frame.right-panel.buttons.open-documentation.text"));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setIconTextGap(15);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.TOP);
        button.setForeground(ColorsManager.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Open documentation");
        try{
            Desktop.getDesktop().browse(LinksManager.getLinkValue(Link.DOCUMENTATION));
        } catch (IOException ex) {
            throw new UrlBrowsingException(ex);
        }
    }
}
