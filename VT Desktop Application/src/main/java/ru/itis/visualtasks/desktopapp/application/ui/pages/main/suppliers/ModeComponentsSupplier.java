package ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers;

import ru.itis.visualtasks.desktopapp.application.entities.project.RecentList;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Button;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.buttons.RecentProjectButton;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.EmptyRecentListPanel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.dialogs.recent.RecentListDialog;

import javax.swing.*;
import java.util.List;

public abstract class ModeComponentsSupplier {


    public abstract ImageIcon getLeftPanelTitleIcon();
    public abstract ImageIcon getRightPanelTitleIcon();
    public abstract String getLeftPanelIconTitle();
    public abstract String getLeftPanelIconDescription();
    public abstract List<Button> getLeftPanelButtons();
    public abstract List<Button> getRightPanelButtons();
    public abstract String getRecentDialogTitle();
    public abstract List<JComponent> getRecentPageContent(RecentListDialog recentListDialog);
    public abstract String getEmptyRecentListText();

    protected List<JComponent> recentListToComponents(RecentList recentList, RecentListDialog recentListDialog){
        if (recentList.getContent().isEmpty()){
            return List.of(
                    new EmptyRecentListPanel().getComponent()
            );
        } else {
            return recentList.getContent().entrySet().stream()
                    .sorted((a1, a2) -> Long.compare(a2.getValue().getTimeStamp(), a1.getValue().getTimeStamp()))
                    .map(entry -> new RecentProjectButton(entry.getValue().getProjectName(), entry.getKey()))
                    .map(entry -> {
                        entry.setRecentListDialog(recentListDialog);
                        return entry.getComponent();
                    })
                    .toList();
        }
    }

}
