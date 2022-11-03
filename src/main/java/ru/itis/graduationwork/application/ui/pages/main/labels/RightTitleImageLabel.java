package ru.itis.graduationwork.application.ui.pages.main.labels;

import ru.itis.graduationwork.application.ui.core.templates.Label;
import ru.itis.graduationwork.application.ui.core.templates.ModeChangeable;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import javax.swing.*;

public class RightTitleImageLabel extends Label implements ModeChangeable {

    private ImageIcon imageIcon;

    public RightTitleImageLabel(){
        super();
        createLabel();
    }

    @Override
    protected void setUpLabel() {
        setLabelContent();
        setLabelStyle();
    }

    private void setLabelContent(){
        loadIcon();
        label.setIcon(imageIcon);
    }

    private void loadIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        imageIcon = supplier.getRightPanelTitleIcon();
    }

    protected void setLabelStyle() {
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void changeMode() {
        createLabel();
    }

}
