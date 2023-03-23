package ru.itis.graduationwork.application.managers.project;

import lombok.Getter;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.compilers.SolutionsFilesCompiler;
import ru.itis.graduationwork.application.compilers.WrappersFilesCompiler;
import ru.itis.graduationwork.application.compilers.java.JavaSolutionsFilesCompiler;
import ru.itis.graduationwork.application.compilers.java.JavaWrappersFilesCompiler;
import ru.itis.graduationwork.application.compilers.python.PythonSolutionFilesCompiler;
import ru.itis.graduationwork.application.compilers.python.PythonWrappersFilesCompiler;
import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.graduationwork.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.exceptions.execution.SolutionFileCompilationException;
import ru.itis.graduationwork.exceptions.execution.SolutionFileExecutingException;
import ru.itis.graduationwork.exceptions.execution.SolutionFileReadingException;
import ru.itis.graduationwork.exceptions.execution.WrappersFilesCompilationException;
import ru.itis.graduationwork.exceptions.unexpected.UnsupportedLanguageException;

import java.util.Map;

public class ProjectTaskFilesManager {

    @Getter
    private static WrappersFilesCompiler wrappersFilesCompiler;
    @Getter
    private static SolutionsFilesCompiler solutionsFilesCompiler;

    public static void setProjectLanguage(Language language){
        switch (language){
            case JAVA -> {
                wrappersFilesCompiler = new JavaWrappersFilesCompiler();
                solutionsFilesCompiler = new JavaSolutionsFilesCompiler();
            }
            case PYTHON -> {
                wrappersFilesCompiler = new PythonWrappersFilesCompiler();
                solutionsFilesCompiler = new PythonSolutionFilesCompiler();
            }
            default -> throw new UnsupportedLanguageException();
        }

    }

    public static void compileWrappersFiles(){
        wrappersFilesCompiler.compileWrappersFiles();
        Map<String, String> wrappersNames = wrappersFilesCompiler.getWrappersNames();
        ConfigManager.setWrappersNames(wrappersNames);
    }

    public static void executeSolutionFile(){
        try{
            compileWrappersFilesIfNeeded();
            compileSolutionFile();
            VisualizationActionsManager.clear();
            solutionsFilesCompiler.executeSolutionFile();
            VisualizationSceneController.onStart();
            VisualizationControlButtonsManager.notifyAboutFileExecution();
        } catch (WrappersFilesCompilationException | SolutionFileCompilationException |
                 SolutionFileExecutingException exception){
            exception.handle();
        }
    }

    public static void executeTestSolutionFile(){
        try{
            compileWrappersFilesIfNeeded();
            compileTestSolutionFile();
            VisualizationActionsManager.clear();
            solutionsFilesCompiler.executeTestSolutionFile();
            VisualizationSceneController.onStart();
            VisualizationControlButtonsManager.notifyAboutFileExecution();
        } catch (WrappersFilesCompilationException | SolutionFileCompilationException |
                 SolutionFileExecutingException | SolutionFileReadingException exception){
            exception.handle();
        }
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
