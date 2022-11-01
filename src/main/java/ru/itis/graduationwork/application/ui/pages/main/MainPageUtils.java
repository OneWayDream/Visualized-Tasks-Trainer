package ru.itis.graduationwork.application.ui.pages.main;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.ComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.DevelopComponentsSupplier;
import ru.itis.graduationwork.application.ui.pages.main.suppliers.StudyComponentsSupplier;

public class MainPageUtils {

    public static ComponentsSupplier supplier;
    public static MainPageFrame.Mode supplierMode;

    public static ComponentsSupplier getComponentSupplier(){
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
            supplier =  new DevelopComponentsSupplier();
        } else {
            supplier =  new StudyComponentsSupplier();
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
