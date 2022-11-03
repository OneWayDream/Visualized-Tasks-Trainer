package ru.itis.graduationwork.application.ui.pages.main;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ModeComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.DevelopModeComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.StudyModeComponentsSupplier;

public class MainPageUtils {

    public static ModeComponentsSupplier supplier;
    public static MainPageFrame.Mode supplierMode;

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
        MainPageFrame.Mode currentMode = getMode();
        if (currentMode == MainPageFrame.Mode.DEVELOP){
            supplier =  new DevelopModeComponentsSupplier();
        } else {
            supplier =  new StudyModeComponentsSupplier();
        }
    }

    public static MainPageFrame.Mode getMode(){
        return getMainPageFrame().getMode();
    }

    public static void changeMode(MainPageFrame.Mode mode){
        getMainPageFrame().changeMode(mode);
    }

    private static MainPageFrame getMainPageFrame(){
        return (MainPageFrame) Application.getCurrentPageFrame();
    }

}
