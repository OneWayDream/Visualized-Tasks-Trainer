package ru.itis.visualtasks.desktopapp.application.compilers.python;

import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.registration.python.PythonVisualizationActionRegistrationManager;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class PythonScryptExecutor {

    public static void executeProcessBuilder(ProcessBuilder processBuilder){
        processBuilder.redirectErrorStream(true);
        try{
            Process process = processBuilder.start();
            List<String> results = readProcessOutput(process.getInputStream());
            if (!results.isEmpty()){
                throw new SolutionFileExecutingException(prepareProcessOutput(results));
            }
            PythonVisualizationActionRegistrationManager.notifyAboutSolutionExecution();
        } catch (IOException exception) {
            throw new SolutionFileExecutingException(exception);
        }
    }

    private static List<String> readProcessOutput(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.lines().toList();
    }

    private static String prepareProcessOutput(List<String> strings){
        return String.join("\n", strings);
    }


}
