package ru.itis.graduationwork.application.ui.pages.main.buttons;

import lombok.Getter;
import ru.itis.graduationwork.application.settings.units.Image;
import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.pages.main.MainFrameIconsConstants;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModeSwitchButton extends Button {

    private static final int ROTATION_SPEED = 10;
    private MainPageFrame.Mode currentMode;

    private final Timer rotateTimer;
    @Getter
    private int angle;

    public ModeSwitchButton(){
        super();
        button = new ButtonImplementation();
        currentMode = MainPageUtils.getMode();
        rotateTimer = new Timer(1, this::rotate);
        angle = 0;
        createButton();
    }

    @Override
    protected void setButtonStyle() {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        setIcon();
    }

    private void setIcon(){
        button.setIcon(getImageIcon());
    }

    private ImageIcon getImageIcon(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getImageIcon(Image.SWITCH,
                MainFrameIconsConstants.LEFT_PANEL_SWITCH_BUTTON_ICON_WIDTH,
                MainFrameIconsConstants.LEFT_PANEL_SWITCH_BUTTON_ICON_HEIGHT);
    }


    private void animateRotation(){
        rotateTimer.start();
    }

    private void rotate(ActionEvent e){
        increaseAngle();
        button.setEnabled(false);
        button.repaint();
        if (angle % 180 == 0){
            rotateTimer.stop();
            changeMode();
            button.setEnabled(true);
        }
    }

    private void increaseAngle(){
        angle+=ROTATION_SPEED;
        if (angle >= 360) {
            angle -= 360;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        animateRotation();
    }

    private void changeMode(){
        if (currentMode == MainPageFrame.Mode.DEVELOP){
            currentMode = MainPageFrame.Mode.STUDY;
        } else {
            currentMode = MainPageFrame.Mode.DEVELOP;
        }
        MainPageUtils.changeMode(currentMode);
    }

    private class ButtonImplementation extends JButton {

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.toRadians(angle),getWidth()/2.0,getHeight()/2.0);
            super.paintComponent(g);
        }

    }

}
