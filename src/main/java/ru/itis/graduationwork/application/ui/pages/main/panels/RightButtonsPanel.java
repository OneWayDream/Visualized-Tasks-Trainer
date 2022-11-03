package ru.itis.graduationwork.application.ui.pages.main.panels;

import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.core.ModeChangeable;
import ru.itis.graduationwork.application.ui.core.Panel;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import java.awt.*;
import java.util.List;

public class RightButtonsPanel extends Panel implements ModeChangeable {

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
        List<Button> buttons = getRightPanelButtons();
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
