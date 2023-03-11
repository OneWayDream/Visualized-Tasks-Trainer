package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class PopupMenu implements Component {

    protected JPopupMenu popupMenu;

    protected PopupMenu(){
        popupMenu = new JPopupMenu();
        initMenu();
    }

    protected abstract void initMenu();

    @Override
    public JComponent getComponent() {
        return popupMenu;
    }

}
