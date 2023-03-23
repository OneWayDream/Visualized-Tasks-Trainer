package ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.actions.deletion;

import lombok.Getter;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.settings.LocalizationManager;
import ru.itis.graduationwork.application.ui.core.ide.explorer.FileWatchService;
import ru.itis.graduationwork.application.ui.core.templates.Dialog;
import ru.itis.graduationwork.exceptions.files.FileDeletionException;

import java.awt.*;

public class DeletionDialog extends Dialog {
    @Getter
    private final String path;
    private boolean isDeleted = false;

    public DeletionDialog(String path){
        this.path = path;
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth() / 4;
        height = getDeviceScreenHeight() / 4;
        title = LocalizationManager.getLocaleTextByKey("ide.content.workspace.explorer.deletion-dialog.title");
    }

    @Override
    public void initDialog(){
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(false);
        super.initDialog();
    }

    @Override
    protected void addComponents() {
        addDeletionConfirmationLabel();
        addDeletionConfirmationButton();
    }

    private void addDeletionConfirmationLabel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 30, 50, 30);
        dialog.add(new DeletionConfirmationLabel().getComponent(), constraint);
    }

    private void addDeletionConfirmationButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.PAGE_END;
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 1;
        dialog.add(new DeletionConfirmationButton(this).getComponent(), c);
    }

    public void delete(){
        try{
            FilesManager.delete(path);
            isDeleted = true;
        } catch (FileDeletionException exception){
            exception.handle();
            FileWatchService.updatePath(path);
        }
    }

    public boolean isDeleted(){
        return isDeleted;
    }

    public void dispose(){
        dialog.dispose();
    }

}
