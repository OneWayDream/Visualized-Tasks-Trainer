package ru.itis.visualtasks.desktopapp.application.ui.pages.main.panels;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.ModeChangeable;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainPageUtils;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import java.awt.*;
import java.util.List;

public class RightButtonsPanel extends ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel implements ModeChangeable {

    public RightButtonsPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        List<ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button> buttons = getRightPanelButtons();
        for (int i = 0; i < buttons.size(); i++) {
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.fill = GridBagConstraints.CENTER;
            constraint.gridx = i;
            constraint.insets = new Insets(0,10,0,10);
            panel.add(buttons.get(i).getComponent(), constraint);
        }
    }

    private List<Button> getRightPanelButtons(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getRightPanelButtons();
    }

    @Override
    public void changeMode() {
        cleanPanel();
        createPanel();
        panel.validate();
    }

    private void cleanPanel(){
        panel.removeAll();
    }

}
