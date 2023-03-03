package ru.itis.graduationwork.application.ui.core.templates;

import ru.itis.graduationwork.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;

public abstract class Chooser {

    protected final JFileChooser chooser;

    public Chooser(){
        chooser = new JFileChooser();
        init();
    }

    protected void init(){
        setChooserSize();
    }

    private void setChooserSize(){
        chooser.setPreferredSize(new Dimension(getDeviceScreenWidth()*2 / 4, getDeviceScreenHeight()*2 / 4));
    }

    private int getDeviceScreenWidth(){
        return getDeviceDisplayMode().getWidth();
    }

    private int getDeviceScreenHeight(){
        return getDeviceDisplayMode().getHeight();
    }

    private DisplayMode getDeviceDisplayMode(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    }

    public abstract void execute();

    protected Component getCurrentFrame(){
        return Application.getCurrentPageFrame().getComponent();
    }

}
