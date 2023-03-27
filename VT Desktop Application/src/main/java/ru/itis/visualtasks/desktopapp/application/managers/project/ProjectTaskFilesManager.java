package ru.itis.visualtasks.desktopapp.application.managers.project;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.compilers.SolutionsFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.WrappersFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaSolutionsFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaWrappersFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonSolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonWrappersFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.WrappersFilesCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedLanguageException;

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
