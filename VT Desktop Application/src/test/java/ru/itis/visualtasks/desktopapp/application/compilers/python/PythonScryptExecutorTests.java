package ru.itis.visualtasks.desktopapp.application.compilers.python;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.exceptions.execution.SolutionFileExecutingException;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PythonScryptExecutorTests {

    private static final String FILES_FOLDER = USER_DIRECTORY + File.separator + "src\\test\\resources\\compilation\\python\\python_scrypt_executor";
    private static final String CORRECT_SCRYPT = "correct_scrypt";
    private static final String INCORRECT_SCRYPT = "incorrect_scrypt";

    @Test
    public void execute_correct_scrypt(){
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", CORRECT_SCRYPT)
                .directory(new File(FILES_FOLDER));
        PythonScryptExecutor.executeProcessBuilder(processBuilder);
    }

    @Test
    public void execute_incorrect_scrypt(){
        ProcessBuilder processBuilder = new ProcessBuilder("py", "-m", INCORRECT_SCRYPT)
                .directory(new File(FILES_FOLDER));
        Assertions.assertThrows(SolutionFileExecutingException.class,
                () -> PythonScryptExecutor.executeProcessBuilder(processBuilder));
    }

}
