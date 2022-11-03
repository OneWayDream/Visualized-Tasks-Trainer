package ru.itis.graduationwork.application.ui.pages.main.buttons;

import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.managers.ColorsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecentProjectButton extends Button {

    private final String name;
    private final String path;

    public RecentProjectButton(String name, String path){
        super();
        this.name = name;
        this.path = path;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setPreferredSize(new Dimension(300, 50));
        button.setBorder(BorderFactory.createLineBorder(ColorsManager.getButtonBordersColor(), 2));
        setIcon();
        setTextStyle();
    }

    private void setIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        button.setIcon(supplier.getImageIcon(Image.ARROW_RIGHT,
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
        System.out.println(name);
    }

}