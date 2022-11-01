package ru.itis.graduationwork.application.ui.pages.main.panels;

import ru.itis.graduationwork.application.ui.core.Label;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ComponentsSupplier;
import ru.itis.graduationwork.application.utils.ColorsManager;

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
        ComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
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
