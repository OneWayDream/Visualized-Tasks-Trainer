package ru.itis.graduationwork.application.compilers.python;

import com.google.gson.Gson;
import ru.itis.graduationwork.application.compilers.WrappersFilesCompiler;
import ru.itis.graduationwork.application.generators.PythonFilesGenerator;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.FilesGeneratorManager;
import ru.itis.graduationwork.exceptions.project.ProjectConfigReadingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class PythonWrappersFilesCompiler extends WrappersFilesCompiler {

    private final String WRAPPERS_ANALYSIS_MODULE = "target.wrappers_analysis";
    private final String WRAPPERS_NAMES_FILE_PATH = "target/wrappers-names.json";
    private final Gson gson;

    private final PythonFilesGenerator pythonFilesGenerator;

    {
        pythonFilesGenerator = (PythonFilesGenerator) FilesGeneratorManager.getFilesGenerator(ConfigManager.getProjectLanguage());
        gson = new Gson();
    }

    @Override
    public void compileWrappersFiles() {
        // don't need to compile
    }

    @Override
    public Map<String, String> getWrappersNames() {
        List<File> modules = loadWrappersCompiledFiles();
        List<String> modulesNames = modules.stream()
                .map(file -> getModuleName(file.getPath()))
                .toList();
        pythonFilesGenerator.generateWrappersAnalysisFile(modulesNames);
        executeWrappersAnalysisFile();
        return readWrappersNamesFile();
    }

    private void executeWrappersAnalysisFile() {
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", WRAPPERS_ANALYSIS_MODULE)
                .directory(new File(ConfigManager.getProjectPath()));
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
    }

    private String getModuleName(String filePath){
        String temp = filePath.substring(ConfigManager.getProjectPath().length() + 1, filePath.length() - 3);
        return temp.replace(File.separatorChar, '.');
    }

    private Map<String, String> readWrappersNamesFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getWrappersNamesFilePath(),
                StandardCharsets.UTF_8))){
            return gson.fromJson(bufferedReader, PythonWrappersNames.class).getContent();
        } catch (IOException ex) {
            throw new ProjectConfigReadingException(ex);
        }
    }

    private String getWrappersNamesFilePath(){
        return ConfigManager.getProjectPath() + File.separator + WRAPPERS_NAMES_FILE_PATH;
    }

    @Override
    protected String getWrappersClassesFolder(){
        return ConfigManager.getProjectPath() + File.separator + WRAPPERS_FOLDER;
    }

    @Override
    protected boolean checkIsCompiledFile(File internalFile) {
        String filePath = internalFile.getPath();
        return filePath.endsWith(".py");
    }


}
