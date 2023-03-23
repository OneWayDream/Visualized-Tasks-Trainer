package ru.itis.graduationwork.application.compilers.java;

import ru.itis.graduationwork.application.compilers.SolutionsFilesCompiler;
import ru.itis.graduationwork.application.entities.project.SolutionScheme;
import ru.itis.graduationwork.application.loaders.ProjectClassLoader;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.exceptions.execution.JavaFileCompilationException;
import ru.itis.graduationwork.exceptions.execution.SolutionFileCompilationException;
import ru.itis.graduationwork.exceptions.execution.SolutionFileExecutingException;

public class JavaSolutionsFilesCompiler extends SolutionsFilesCompiler {

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
        ProjectClassLoader classLoader = new ProjectClassLoader();
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
