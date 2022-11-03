package ru.itis.graduationwork.application.ui.pages.main.panels;

import ru.itis.graduationwork.application.ui.core.templates.Button;
import ru.itis.graduationwork.application.ui.core.templates.ModeChangeable;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import java.awt.*;
import java.util.List;

public class LeftButtonsPanel extends Panel implements ModeChangeable {

    public LeftButtonsPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        List<Button> buttons = getLeftPanelButtons();
        for (int i = 0; i < buttons.size(); i++) {
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.fill = GridBagConstraints.CENTER;
            constraint.gridy = i;
            constraint.insets = new Insets(20,0,0,0);
            panel.add(buttons.get(i).getComponent(), constraint);
        }
    }

    private List<Button> getLeftPanelButtons(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getLeftPanelButtons();
    }

    @Override
    public void changeMode() {
        cleanPanel();
        createPanel();
        panel.repaint();
        panel.validate();
    }

    private void cleanPanel(){
        panel.removeAll();
    }

}
