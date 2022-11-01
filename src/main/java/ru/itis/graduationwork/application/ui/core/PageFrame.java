package ru.itis.graduationwork.application.ui.core;

import lombok.Getter;
import ru.itis.graduationwork.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class PageFrame {

    @Getter
    protected final JFrame frame;

    protected PageFrame(){
        this.frame = new JFrame();
    }

    public void initPage(){
        setFrameSettings();
        setOnCloseAction();
        addComponents();
        showPageFrame();
    }

    private void setFrameSettings(){
        setFrameSize();
        setFrameOnDisplayCenter();
    }

    private void setFrameSize(){
        frame.setSize(getDeviceScreenWidth()*3 / 4, getDeviceScreenHeight()*3 / 4);
    }

    private int getDeviceScreenWidth(){
        return getDeviceDisplayMode().getWidth();
    }

    private int getDeviceScreenHeight(){
        return getDeviceDisplayMode().getHeight();
    }

    private void setFrameOnDisplayCenter(){
        frame.setLocationRelativeTo(null);
    }

    private DisplayMode getDeviceDisplayMode(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    }

    private void setOnCloseAction(){
        frame.addWindowListener(new FrameListener());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected abstract void addComponents();

    private void showPageFrame(){
        frame.setVisible(true);
    }

    public void dispose(){
        frame.dispose();
    }

    public Component getComponent(){
        return frame;
    }

    protected static class FrameListener extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent event) {
            Application.close();
        }
    }

}
