package ru.itis.visualtasks.desktopapp.application.ui.pages.main.labels;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Label;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainPageUtils;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;

import javax.swing.*;
import java.awt.*;

public class LeftDescriptionLabel extends Label {

    public LeftDescriptionLabel(){
        super();
        createLabel();
        label.doLayout();
    }

    @Override
    protected void setUpLabel() {
        setLabelContent();
        setLabelStyle();
    }

    private void setLabelContent(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        label.setText(supplier.getLeftPanelIconDescription());
        label.setForeground(ColorsManager.getTextColor());
    }

    private void setLabelStyle(){
        setLabelPosition();
        setTextStyle();
    }

    private void setLabelPosition(){
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setTextStyle(){
        label.setFont(new Font("Comic Sans", Font.ITALIC, 22));
    }

}
