package ru.itis.graduationwork.application.ui.pages.main.dialogs;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.core.Button;
import ru.itis.graduationwork.application.ui.core.Dialog;
import ru.itis.graduationwork.application.ui.pages.main.MainPageUtils;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;

import java.awt.*;
import java.util.List;

public class RecentListDialog extends Dialog {

    public RecentListDialog(){
        super(Application.getCurrentPageFrame().getFrame());
    }

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
        List<Button> buttons = getRecentButtons();
        for (int i = 0; i < buttons.size(); i++) {
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.fill = GridBagConstraints.CENTER;
            constraint.gridy = i;
            constraint.insets = new Insets(10,0,0,0);
            dialog.add(buttons.get(i).getComponent(), constraint);
        }
    }

    private List<Button> getRecentButtons(){
        ModeComponentsSupplier supplier = MainPageUtils.getComponentSupplier();
        return supplier.getRecentPageButtons();
    }

}
