package ru.itis.visualtasks.desktopapp.application.managers.project;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.compilers.SolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.WrappersFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaSolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.java.JavaWrapperFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonSolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.compilers.python.PythonWrapperFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationActionsManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationArgumentsStorage;
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
    private static SolutionFilesCompiler solutionFilesCompiler;

    public static void setProjectLanguage(Language language){
        switch (language){
            case JAVA -> {
                wrappersFilesCompiler = new JavaWrapperFilesCompiler();
                solutionFilesCompiler = new JavaSolutionFilesCompiler();
            }
            case PYTHON -> {
                wrappersFilesCompiler = new PythonWrapperFilesCompiler();
                solutionFilesCompiler = new PythonSolutionFilesCompiler();
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
            VisualizationArgumentsStorage.reset();
            solutionFilesCompiler.executeSolutionFile();
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
            VisualizationArgumentsStorage.reset();
            solutionFilesCompiler.executeTestSolutionFile();
            VisualizationSceneController.onStart();
            VisualizationControlButtonsManager.notifyAboutFileExecution();
        } catch (WrappersFilesCompilationException | SolutionFileCompilationException |
                 SolutionFileExecutingException | SolutionFileReadingException exception){
            exception.handle();
        }
    }

    public static void compileSolutionFile(){
        solutionFilesCompiler.compileSolutionFile();
    }

    public static void compileTestSolutionFile(){
        solutionFilesCompiler.compileTestSolutionFile();
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
