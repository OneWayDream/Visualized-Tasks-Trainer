package ru.itis.visualtasks.desktopapp.application.ui.pages.main;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.DevelopModeComponentsSupplier;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.suppliers.StudyModeComponentsSupplier;

public class MainPageUtils {

    public static ModeComponentsSupplier supplier;
    public static Mode supplierMode;

    public static ModeComponentsSupplier getComponentSupplier(){
        if (!isComponentSupplierPresent()){
            createComponentSupplier();
        }
        return supplier;

    }

    private static boolean isComponentSupplierPresent(){
        return (supplier != null) && (getMode().equals(supplierMode));
    }

    private static void createComponentSupplier(){
        Mode currentMode = getMode();
        if (currentMode == Mode.DEVELOP){
            supplier =  new DevelopModeComponentsSupplier();
        } else {
            supplier =  new StudyModeComponentsSupplier();
        }
    }

    public static Mode getMode(){
        return Application.getMode();
    }

    public static void changeMode(Mode mode){
        Application.changeMode(mode);
    }

}
