package ru.itis.graduationwork.application.ui.core.templates;

import lombok.Getter;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.managers.ImagesManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.PageType;

import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class PageFrame {

    @Getter
    protected JFrame frame;
    @Getter
    protected int width;
    @Getter
    protected int height;
    @Getter
    protected PageType pageType;
    protected String title;

    protected PageFrame(){
        this.frame = new JFrame();
        initFields();
    }

    protected abstract void initFields();

    public abstract PageFrame copy();

    public void initPage(){
        initHeader();
        setFrameSettings();
        setOnCloseAction();
        addComponents();
        showPageFrame();
    }

    protected void initHeader(){
        frame.setTitle(title);
        frame.setForeground(ColorsManager.getTextColor());
        frame.setIconImage(ImagesManager.getApplicationIcon().getImage());
    }

    private void setFrameSettings(){
        setFrameSize();
        setFrameOnDisplayCenter();
    }

    private void setFrameSize(){
        frame.setSize(width, height);
    }

    public void changeMode(Mode mode){

    }

    protected int getDeviceScreenWidth(){
        return getDeviceDisplayMode().getWidth();
    }

    protected int getDeviceScreenHeight(){
        return getDeviceDisplayMode().getHeight();
    }

    private void setFrameOnDisplayCenter(){
        frame.setLocationRelativeTo(null);
    }

    private DisplayMode getDeviceDisplayMode(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    }

    protected void setOnCloseAction(){
        frame.addWindowListener(new FrameListener());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
            Application.exit();
        }
    }

}
