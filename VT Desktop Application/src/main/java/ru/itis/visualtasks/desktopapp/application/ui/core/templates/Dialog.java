package ru.itis.visualtasks.desktopapp.application.ui.core.templates;

import ru.itis.visualtasks.desktopapp.application.Application;

import javax.swing.*;
import java.awt.Component;
import java.awt.*;

public abstract class Dialog extends Component {

    protected final JDialog dialog;

    protected String title;
    protected int width;
    protected int height;

    protected Dialog(){
        dialog = new JDialog(Application.getCurrentPageFrame().getFrame(), true);
        initFields();
    }

    protected abstract void initFields();

    public void initDialog(){
        setDialogSettings();
        setOnCloseAction();
        addComponents();
        showDialog();
    }

    private void setDialogSettings(){
        dialog.setTitle(title);
        setDialogSize();
        setDialogOnDisplayCenter();
    }

    private void setDialogSize(){
        dialog.setSize(width, height);
    }

    protected int getDeviceScreenWidth(){
        return getDeviceDisplayMode().getWidth();
    }

    protected int getDeviceScreenHeight(){
        return getDeviceDisplayMode().getHeight();
    }

    private DisplayMode getDeviceDisplayMode(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    }

    private void setDialogOnDisplayCenter(){
        dialog.setLocationRelativeTo(null);
    }

    private void setOnCloseAction(){
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    protected abstract void addComponents();

    private void showDialog(){
        dialog.setVisible(true);
    }

}
