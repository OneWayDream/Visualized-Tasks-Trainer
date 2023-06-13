package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.explorer.popup;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PopupMenu;

public class FileTreePopupMenu extends PopupMenu {
    @Getter
    private final String triggerPath;

    public FileTreePopupMenu(String triggerPath){
        super();
        this.triggerPath = triggerPath;
    }

    @Override
    protected void initMenu() {
        setPopupMenuStyle();
        popupMenu.add(new CreateFileAbstractAction(this));
        popupMenu.add(new CreateFolderAbstractAction(this));
        popupMenu.add(new DeleteAbstractAction(this));
    }

    private void setPopupMenuStyle(){
        popupMenu.setForeground(ColorsManager.getTextColor());
    }

}
