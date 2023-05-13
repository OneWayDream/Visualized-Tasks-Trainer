package ru.itis.visualtasks.desktopapp.application.compilers.python;

import ru.itis.visualtasks.desktopapp.application.compilers.SolutionFilesCompiler;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.python.PythonVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.PythonFilePreparationException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileAlreadyExistsException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FolderAlreadyExistsException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FolderCreationException;

import java.io.File;
import java.util.Map;

public class PythonSolutionFilesCompiler extends SolutionFilesCompiler {

    private final String SOLUTION_MODULE = "target.solution";
    private final String TEST_SOLUTION_MODULE = "target.test_solution";

    @Override
    public void executeSolutionFile() {
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", SOLUTION_MODULE)
                .directory(new File(ConfigManager.getProjectPath()));
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
        PythonVisualizationActionRegistrationManager.notifyAboutSolutionExecution();
    }

    @Override
    public void executeTestSolutionFile() {
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", TEST_SOLUTION_MODULE)
                .directory(new File(ConfigManager.getProjectPath()));
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
        PythonVisualizationActionRegistrationManager.notifyAboutSolutionExecution();
    }

    @Override
    public void compileSolutionFile() {
        String solutionFileContent = getSolutionFileContent();
        solutionFileContent = prepareSolutionContent(solutionFileContent);
        writeTargetSolutionFile(solutionFileContent);
    }

    private String getSolutionFileContent() {
        String solutionFilePath = getSolutionFilePath();
        return FilesManager.readFileAsString(solutionFilePath);
    }

    private String getSolutionFilePath() {
        return ConfigManager.getProjectPath() + File.separator + ProjectFilesManager.getSolutionFileName();
    }

    private void writeTargetSolutionFile(String solutionFileContent) {
        createTargetSolutionFile();
        FilesManager.writeFile(getTargetSolutionFilePath(), solutionFileContent);
    }

    private String getTargetSolutionFilePath() {
        return getTargetDirectoryPath() + File.separator + ProjectFilesManager.getSolutionFileName();
    }

    private String getTargetDirectoryPath(){
        return ConfigManager.getProjectPath() + File.separator + "target";
    }

    private void createTargetSolutionFile() {
        createTargetFolderIfNotExists();
        try{
            FilesManager.createFile(getTargetSolutionFilePath());
        } catch (FileAlreadyExistsException exception){
            //ignore - it's fine
        } catch (FileCreationException exception){
            throw new PythonFilePreparationException(exception);
        }
    }

    private void createTargetFolderIfNotExists() {
        String targetDirectoryPath = getTargetDirectoryPath();
        try{
            FilesManager.createDirectory(targetDirectoryPath);
        } catch (FolderAlreadyExistsException exception){
            //ignore - it's fine
        } catch (FolderCreationException exception){
            throw new PythonFilePreparationException(exception);
        }
    }


    @Override
    public void compileTestSolutionFile() {
        String testSolutionFileContent = getTestSolutionFileContent();
        testSolutionFileContent = prepareSolutionContent(testSolutionFileContent);
        writeTargetTestSolutionFile(testSolutionFileContent);
    }

    private String getTestSolutionFileContent() {
        String testSolutionFilePath = getTestSolutionFilePath();
        return FilesManager.readFileAsString(testSolutionFilePath);
    }

    private String getTestSolutionFilePath() {
        return ConfigManager.getProjectPath() + File.separator + ProjectFilesManager.getTestSolutionFileName();
    }

    private String prepareSolutionContent(String solutionContent) {
        solutionContent = solutionContent + "\n    write_visualization_actions()\n";
        solutionContent = handleWrappersNames(solutionContent);
        return solutionContent;
    }

    private String handleWrappersNames(String solutionContent) {
        Map<String, String> wrappersNames = ConfigManager.getWrappersNames();
        int importIndex = solutionContent.lastIndexOf("import ");
        String temp = solutionContent.substring(importIndex);
        String header = solutionContent.substring(0, importIndex + temp.indexOf('\n'));
        String code = solutionContent.substring(importIndex + temp.indexOf('\n'));
        if (wrappersNames != null){
            for (String wrapperName: wrappersNames.keySet()){
                code = code.replace(wrapperName, wrappersNames.get(wrapperName));
            }
        }
        return header + code;
    }

    private void writeTargetTestSolutionFile(String testSolutionFileContent) {
        createTargetTestSolutionFile();
        FilesManager.writeFile(getTargetTestSolutionFilePath(), testSolutionFileContent);
    }

    private void createTargetTestSolutionFile() {
        try{
            FilesManager.createDirectory(getTargetDirectoryPath());
            FilesManager.createFile(getTargetTestSolutionFilePath());
        } catch (FileAlreadyExistsException | FolderAlreadyExistsException exception){
            //ignore - it's fine
        } catch (FileCreationException | FolderCreationException exception){
            throw new PythonFilePreparationException(exception);
        }
    }

    private String getTargetTestSolutionFilePath(){
        return getTargetDirectoryPath() + File.separator + ProjectFilesManager.getTestSolutionFileName();
    }


}
