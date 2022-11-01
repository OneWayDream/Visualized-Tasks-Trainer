package ru.itis.graduationwork.application.ui.pages.main.labels;

import ru.itis.graduationwork.application.ui.core.Label;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ComponentsSupplier;
import ru.itis.graduationwork.application.utils.ColorsManager;

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
        ComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
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
