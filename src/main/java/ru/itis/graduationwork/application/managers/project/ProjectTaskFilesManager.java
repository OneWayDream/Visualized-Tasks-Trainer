package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.compilers.SolutionsFilesCompiler;
import ru.itis.graduationwork.application.compilers.WrappersFilesCompiler;
import ru.itis.graduationwork.application.compilers.java.JavaSolutionsFilesCompiler;
import ru.itis.graduationwork.application.compilers.java.JavaWrappersFilesCompiler;
import ru.itis.graduationwork.application.entities.Language;
import ru.itis.graduationwork.application.settings.Mode;

public class ProjectTaskFilesManager {

    private static WrappersFilesCompiler wrappersFilesCompiler;
    private static SolutionsFilesCompiler solutionsFilesCompiler;

    public static void setProjectLanguage(Language language){
        wrappersFilesCompiler = new JavaWrappersFilesCompiler();
        solutionsFilesCompiler = new JavaSolutionsFilesCompiler();
    }

    public static void compileWrappersFiles(){
        wrappersFilesCompiler.compileWrappersFiles();
    }

    public static void executeSolutionFile(){
        compileWrappersFilesIfNeeded();
        compileSolutionFile();
        VisualizationActionsManager.clear();
        solutionsFilesCompiler.executeSolutionFile();
        VisualizationSceneController.onStart();
    }

    public static void executeTestSolutionFile(){
        compileWrappersFilesIfNeeded();
        compileTestSolutionFile();
        VisualizationActionsManager.clear();
        solutionsFilesCompiler.executeTestSolutionFile();
        VisualizationSceneController.onStart();
    }

    public static void compileSolutionFile(){
        solutionsFilesCompiler.compileSolutionFile();
    }

    public static void compileTestSolutionFile(){
        solutionsFilesCompiler.compileTestSolutionFile();
    }

    private static void compileWrappersFilesIfNeeded(){
        if (isDevelopMode()){
            compileWrappersFiles();
        }
    }

    private static boolean isDevelopMode(){
        return Application.getMode() == Mode.DEVELOP;
    }

}
