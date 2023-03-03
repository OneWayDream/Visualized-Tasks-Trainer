package ru.itis.graduationwork.application.ui.pages.main.dialogs.recent;

import ru.itis.graduationwork.application.ui.core.templates.Dialog;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecentListDialog extends Dialog {

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 2;
        height = getDeviceScreenHeight() / 2;
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        title = supplier.getRecentDialogTitle();
    }

    @Override
    public void initDialog() {
        dialog.setLayout(new GridBagLayout());
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        List<JComponent> components = getRecentButtons();
        for (int i = 0; i < components.size(); i++) {
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.fill = GridBagConstraints.CENTER;
            constraint.gridy = i;
            constraint.insets = new Insets(10,0,0,0);
            dialog.add(components.get(i), constraint);
        }
    }

    private List<JComponent> getRecentButtons(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getRecentPageContent(this);
    }

    public void reload(){
        dialog.dispose();
        new RecentListDialog().initDialog();
    }

}
