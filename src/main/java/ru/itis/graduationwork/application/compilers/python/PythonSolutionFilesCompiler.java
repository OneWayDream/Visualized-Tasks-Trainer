package ru.itis.graduationwork.application.compilers.python;

import ru.itis.graduationwork.application.compilers.SolutionsFilesCompiler;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.exceptions.execution.PythonFilePreparationException;
import ru.itis.graduationwork.exceptions.files.FileAlreadyExistsException;
import ru.itis.graduationwork.exceptions.files.FileCreationException;

import java.io.File;
import java.util.Map;

public class PythonSolutionFilesCompiler extends SolutionsFilesCompiler {

    private final String SOLUTION_MODULE = "target.solution";
    private final String TEST_SOLUTION_MODULE = "target.test_solution";

    @Override
    public void executeSolutionFile() {
        ProcessBuilder processBuilder = new ProcessBuilder("py -m", SOLUTION_MODULE);
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
    }

    @Override
    public void executeTestSolutionFile() {
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", TEST_SOLUTION_MODULE)
                .directory(new File(ConfigManager.getProjectPath()));
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
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
        return ConfigManager.getProjectPath() + File.separator + "target"
                + File.separator + ProjectFilesManager.getSolutionFileName();
    }

    private void createTargetSolutionFile() {
        try{
            FilesManager.createFile(getSolutionFilePath());
        } catch (FileAlreadyExistsException exception){
            //ignore - it's fine
        } catch (FileCreationException exception){
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
        for (String wrapperName: wrappersNames.keySet()){
            code = code.replace(wrapperName, wrappersNames.get(wrapperName));
        }
        return header + code;
    }

    private void writeTargetTestSolutionFile(String testSolutionFileContent) {
        createTargetTestSolutionFile();
        FilesManager.writeFile(getTargetTestSolutionFilePath(), testSolutionFileContent);
    }

    private void createTargetTestSolutionFile() {
        try{
            FilesManager.createFile(getTargetTestSolutionFilePath());
        } catch (FileAlreadyExistsException exception){
            //ignore - it's fine
        } catch (FileCreationException exception){
            throw new PythonFilePreparationException(exception);
        }
    }

    private String getTargetTestSolutionFilePath(){
        return ConfigManager.getProjectPath() + File.separator + "target"
                + File.separator + ProjectFilesManager.getTestSolutionFileName();
    }


}
