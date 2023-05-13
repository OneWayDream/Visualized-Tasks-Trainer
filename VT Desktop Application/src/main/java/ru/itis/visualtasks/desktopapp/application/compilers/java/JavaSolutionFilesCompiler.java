package ru.itis.visualtasks.desktopapp.application.compilers.java;

import ru.itis.visualtasks.desktopapp.application.compilers.SolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;
import ru.itis.visualtasks.desktopapp.application.loaders.ProjectClassLoader;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.JavaFileCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileCompilationException;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;

public class JavaSolutionFilesCompiler extends SolutionFilesCompiler {

    private static final String SOLUTION_CLASS_NAME = "Solution";
    private static final String TEST_SOLUTION_CLASS_NAME = "TestSolution";
    private final String SOLUTION_FILE_PATH = ProjectFilesManager.getSolutionFilePath();
    private final String TEST_SOLUTION_FILE_PATH = ProjectFilesManager.getTestSolutionFilePath();

    @Override
    public void executeSolutionFile() {
        executeSolutionSchemeFile(SOLUTION_CLASS_NAME);
    }

    @Override
    public void executeTestSolutionFile() {
        executeSolutionSchemeFile(TEST_SOLUTION_CLASS_NAME);
    }

    private void executeSolutionSchemeFile(String className){
        ProjectClassLoader classLoader = new ProjectClassLoader(JavaSolutionFilesCompiler.class.getClassLoader());
        try {
            Class<?> customPanelClass = Class.forName(className, true,
                    classLoader);
            SolutionScheme solutionScheme = (SolutionScheme) customPanelClass.getDeclaredConstructor().newInstance();
            solutionScheme.execute();
        } catch (Exception exception) {
            throw new SolutionFileExecutingException(exception);
        }
    }

    @Override
    public void compileSolutionFile() {
        try{
            JavaClassesCompiler.compileFile(SOLUTION_FILE_PATH);
        } catch (JavaFileCompilationException exception){
            throw new SolutionFileCompilationException(exception);
        }
    }

    @Override
    public void compileTestSolutionFile() {
        try{
            JavaClassesCompiler.compileFile(TEST_SOLUTION_FILE_PATH);
        } catch (JavaFileCompilationException exception){
            throw new SolutionFileCompilationException(exception);
        }
    }
}
