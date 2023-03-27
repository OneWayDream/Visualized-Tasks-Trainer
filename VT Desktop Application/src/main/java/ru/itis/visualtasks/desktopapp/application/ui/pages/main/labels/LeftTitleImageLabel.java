package ru.itis.visualtasks.desktopapp.application.ui.pages.main.labels;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Label;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainPageUtils;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;

import javax.swing.*;
import java.awt.*;

public class LeftTitleImageLabel extends Label {

    public LeftTitleImageLabel(){
        super();
        createLabel();
        label.doLayout();
    }

    @Override
    protected void setUpLabel(){
        setLabelContent();
        setLabelStyle();
    }

    private void setLabelContent(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        label.setIcon(supplier.getLeftPanelTitleIcon());
        label.setText(supplier.getLeftPanelIconTitle());
    }

    protected void setLabelStyle() {
        setLabelPosition();
        setTextPosition();
        setTextStyle();
    }

    private void setLabelPosition(){
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setTextPosition(){
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(15);
    }

    private void setTextStyle(){
        label.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        label.setForeground(ColorsManager.getTextColor());
    }


}
